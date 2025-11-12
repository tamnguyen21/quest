import { Component, HostListener, Input } from '@angular/core';

@Component({
  selector: 'asc-bold',
  imports: [],
  templateUrl: './asc-bold.html',
  styleUrl: './asc-bold.css',
})
export class AscBold {
  protected compteur: number = 0;

  // @Input('titre') maVarInput: string = "";
  @Input() titre: string = "";

  @HostListener('click')
  protected onClick(): void {
    this.compteur++;

    console.log(this.compteur);
  }
}
