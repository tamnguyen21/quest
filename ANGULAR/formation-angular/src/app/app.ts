import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { Todo } from './todo';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    FormsModule,
    CommonModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = 'formation-angular';

  protected prenom: string = "Jérémy";
  protected couleur: string = "black";
  protected todo: Todo = new Todo(1, "Le titre du TODO", false, 1);

  // Tableau de Todo
  // protected todos: Array<Todo> = new Array();
  protected todos: Todo[] = [
    new Todo(1, "Le titre", true, 1),
    new Todo(2, "Plier son parachute", false, 1)
  ];

  public changePrenom() {
    this.prenom = "Nouveau prénom !";
  }

  public onInput(event: any) {
    // On va donner la valeur de l'input dans le prénom
    this.prenom = event.target.value;
  }

  public ajouterTodo() {
    // On ajoute le Todo à la liste
    // this.todos.push(new Todo(
    //   this.todo.id,
    //   this.todo.title,
    //   this.todo.completed,
    //   1,
    // ));

    // this.todo.id = 1;
    // this.todo.title = "";
    // this.todo.completed = false;

    this.todos.push(this.todo);

    this.todo = new Todo(1, "", false, 1);
  }
}
