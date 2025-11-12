import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AscBold } from './asc-bold/asc-bold';
import { Navigation } from './navigation/navigation';
import { DemoPipePipe } from './demo-pipe-pipe';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    FormsModule,
    CommonModule,
    AscBold,
    Navigation,
    DemoPipePipe
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = 'formation-angular';

  protected prenom: string = "Jérémy";
  protected couleur: string = "black";

  public changePrenom() {
    this.prenom = "Nouveau prénom !";
  }

  public onInput(event: any) {
    // On va donner la valeur de l'input dans le prénom
    this.prenom = event.target.value;
  }
}
