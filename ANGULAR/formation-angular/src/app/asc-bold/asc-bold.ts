import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'asc-bold',
  imports: [],
  templateUrl: './asc-bold.html',
  styleUrl: './asc-bold.css',
})
export class AscBold {
  protected compteur: number = 0;

  @HostListener('click')
  protected onClick(): void {
    this.compteur++;

    console.log(this.compteur);
  }
}
