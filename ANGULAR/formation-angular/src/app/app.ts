import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { Todo } from './todo';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    FormsModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = 'formation-angular';

  protected prenom: string = "Jérémy";
  protected couleur: string = "black";
  protected todo: Todo = new Todo(1, "Le titre du TODO", false, 1);

  public changePrenom() {
    this.prenom = "Nouveau prénom !";
  }

  public onInput(event: any) {
    // On va donner la valeur de l'input dans le prénom
    this.prenom = event.target.value;
  }

}
