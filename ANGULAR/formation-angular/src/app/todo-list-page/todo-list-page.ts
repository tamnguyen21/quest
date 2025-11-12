import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Todo } from '../todo';
import { TodoService } from '../todo-service';
import { TodoStatePipe } from '../todo-state-pipe';

@Component({
  selector: 'app-todo-list-page',
  imports: [
    CommonModule, FormsModule, TodoStatePipe, RouterLink
  ],
  templateUrl: './todo-list-page.html',
  styleUrl: './todo-list-page.css',
})
export class TodoListPage implements OnInit {
  protected todo: Todo = new Todo(0, "Le titre du TODO", false, 1);
  protected todos!: Todo[];

  constructor(private route: ActivatedRoute, private title: Title, private todoService: TodoService) { }

  ngOnInit(): void {
    this.title.setTitle("Liste des Todos");

    this.todos = this.todoService.findAll();

    this.route.queryParams.subscribe((params: any) => {
      console.log(params);
      // console.log("ID = " + params['id']);
      console.log("ID = " + params.id);
    });
  }

  public trackByTodo(index: number, todo: Todo): number {
    return todo.id;
  }

  public ajouterTodo() {
    this.todoService.save(this.todo);
    this.todo = new Todo(0, "", false, 1);
  }

  public modifierTodo() {
    this.todoService.save(this.todo);
    this.todo = new Todo(0, "", false, 1);
  }

  public editTodo(todo: Todo): void {
    // Clone du TODO pour l'édition
    this.todo = new Todo(todo.id, todo.title, todo.completed, todo.userId);
  }

  public deleteTodo(todo: Todo): void {
    this.todoService.deleteById(todo.id);
  }
}
