import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Todo } from '../todo';

@Component({
  selector: 'app-http-page',
  imports: [ CommonModule ],
  templateUrl: './http-page.html',
  styleUrl: './http-page.css',
})
export class HttpPage {
  protected todos!: Todo[];
  protected todos$!: Observable<Todo[]>;

  constructor(private http: HttpClient) {
    // Soit on s'inscrit à la main
    this.http.get<Todo[]>('https://jsonplaceholder.typicode.com/todos').subscribe(resp => {
      console.log(resp);
      this.todos = resp;
    });

    // Soit on laisse Angular le faire pour nous
    this.todos$ = this.http.get<Todo[]>('https://jsonplaceholder.typicode.com/todos');
  }
}
