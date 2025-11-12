import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'demoPipe'
})
export class DemoPipePipe implements PipeTransform {
  transform(value: string, ...args: string[]): string {
    if (args[0] === "test") {
      return "THE TEST";
    }

    return "DEMONSTRATION PIPE -> " + value + " -> " + args[0];
  }
}
