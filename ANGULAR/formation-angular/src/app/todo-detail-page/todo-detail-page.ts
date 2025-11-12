import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { TodoService } from '../todo-service';
import { Todo } from '../todo';
import { TodoStatePipe } from '../todo-state-pipe';

@Component({
  selector: 'app-todo-detail-page',
  imports: [ TodoStatePipe ],
  templateUrl: './todo-detail-page.html',
  styleUrl: './todo-detail-page.css',
})
export class TodoDetailPage implements OnInit {
  protected todo!: Todo | null;

  constructor(private route: ActivatedRoute, private title: Title, private todoService: TodoService) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: any) => {
      this.todoService.findById(params.id).subscribe(todo => {
        this.todo = todo;

        if (this.todo) {
          this.title.setTitle("Détail du Todo #" + this.todo.id);
        }

        else {
          this.title.setTitle("Todo inconnu");
        }
      });
    });
  }
}
