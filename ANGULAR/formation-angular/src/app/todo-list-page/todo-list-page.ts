import { Component } from '@angular/core';
import { Todo } from '../todo';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-todo-list-page',
  imports: [
    CommonModule, FormsModule
  ],
  templateUrl: './todo-list-page.html',
  styleUrl: './todo-list-page.css',
})
export class TodoListPage {
  protected todo: Todo = new Todo(1, "Le titre du TODO", false, 1);

  // Tableau de Todo
  // protected todos: Array<Todo> = new Array();
  protected todos: Todo[] = [
    new Todo(1, "Le titre", true, 1),
    new Todo(2, "Plier son parachute", false, 1)
  ];

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
