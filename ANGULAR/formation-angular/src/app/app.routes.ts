import { Routes } from '@angular/router';
import { HomePage } from './home-page/home-page';
import { TodoListPage } from './todo-list-page/todo-list-page';
import { TodoDetailPage } from './todo-detail-page/todo-detail-page';
import { ObservablePage } from './observable-page/observable-page';

export const routes: Routes = [
    { path: 'home', component: HomePage },
    { path: 'todos', component: TodoListPage },
    { path: 'todos/:id', component: TodoDetailPage },
    { path: 'observe', component: ObservablePage }
];
