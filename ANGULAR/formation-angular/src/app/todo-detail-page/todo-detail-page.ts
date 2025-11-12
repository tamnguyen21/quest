import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-todo-detail-page',
  imports: [],
  templateUrl: './todo-detail-page.html',
  styleUrl: './todo-detail-page.css',
})
export class TodoDetailPage implements OnInit {
  protected todoId: number = 0;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: any) => {
      this.todoId = params.id;
    });
  }
}
