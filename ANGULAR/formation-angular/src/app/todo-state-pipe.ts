import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'todoState'
})
export class TodoStatePipe implements PipeTransform {
  transform(value: boolean, ...args: string[]): string {
    if (args.length > 0 && args[0] === "string") {
      if (value) {
        return "Terminé !";
      }

      return "Pas terminé";
    }

    // Dans l'autre cas, c'est la couleur qu'on retourne
    return (value) ? "hsl(153 48% 49%)" : "hsl(341 79% 53%)";
  }
}
