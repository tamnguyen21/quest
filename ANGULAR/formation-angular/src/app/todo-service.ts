import { Injectable } from '@angular/core';
import { Todo } from './todo';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private apiUrl: string = "https://jsonplaceholder.typicode.com/todos";

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.apiUrl);
  }

  public findAllByNom(nom: string): Observable<Todo[]> {
    return this.findAll().pipe( // pipe permet de modifier le flux, en filtrant, en le transformant, etc.

      // On prend le résultat, et on le transforme en une nouvelle liste filtrée
      map(todos => todos.filter(todo => todo.title.includes(nom)))
    );
  }

  public findById(id: number): Observable<Todo> {
    return this.http.get<Todo>(`${ this.apiUrl }/${ id }`);
  }

  public save(todo: Todo): Observable<Todo> {
    const payload = {
      id: todo.id,
      title: todo.title,
      completed: todo.completed,
      userId: todo.userId
    };

    if (!todo.id) {
      return this.http.post<Todo>(this.apiUrl, payload);
    }

    return this.http.put<Todo>(`${ this.apiUrl }/${ todo.id }`, payload);
  }

  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ id }`);
  }
}
