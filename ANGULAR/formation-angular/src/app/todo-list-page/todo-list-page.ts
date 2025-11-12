import { Component, OnInit } from '@angular/core';
import { Todo } from '../todo';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TodoStatePipe } from '../todo-state-pipe';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-todo-list-page',
  imports: [
    CommonModule, FormsModule, TodoStatePipe
  ],
  templateUrl: './todo-list-page.html',
  styleUrl: './todo-list-page.css',
})
export class TodoListPage implements OnInit {
  protected todo: Todo = new Todo(1, "Le titre du TODO", false, 1);

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params: any) => {
      console.log(params);
      // console.log("ID = " + params['id']);
      console.log("ID = " + params.id);
    });
  }

  // Tableau de Todo
  // protected todos: Array<Todo> = new Array();
  protected todos: Todo[] = [
    new Todo(1, "Le titre", true, 1),
    new Todo(2, "Plier son parachute", false, 1)
  ];

  public trackByTodo(index: number, todo: Todo): number {
    return todo.id;
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
