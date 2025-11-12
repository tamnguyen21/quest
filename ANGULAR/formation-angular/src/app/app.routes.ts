import { Routes } from '@angular/router';
import { HomePage } from './home-page/home-page';
import { TodoListPage } from './todo-list-page/todo-list-page';

export const routes: Routes = [
    { path: 'home', component: HomePage },
    { path: 'todos', component: TodoListPage }
];
