import { Injectable } from '@angular/core';
import { Todo } from './todo';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  // Compteur d'id pour "auto-générer"
  private todoId = 3;

  // Tableau de Todo, initialisé avec 2 Todos
  private todos: Todo[] = [
    new Todo(1, "Le titre", true, 1),
    new Todo(2, "Plier son parachute", false, 1)
  ];

  public findAll(): Todo[] {
    return this.todos;
  }

  public findAllByNom(nom: string): Todo[] {
    return this.todos.filter(t => t.title.includes(nom));
  }

  public findById(id: number): Todo | null {
    return this.todos.find(t => t.id == id) ?? null;
  }

  public save(todo: Todo): void {
    if (!todo.id) {
      this.todos.push(new Todo(this.todoId++, todo.title, todo.completed));
    }

    else {
      const updatedTodo: Todo | null = this.findById(todo.id);

      if (updatedTodo) {
        updatedTodo.title = todo.title;
        updatedTodo.completed = todo.completed;
      }
    }
  }

  public deleteById(id: number): void {
    const todoIndex = this.todos.findIndex(t => t.id == id);

    if (todoIndex !== -1) {
      this.todos.splice(todoIndex, 1);
    }
  }
}
