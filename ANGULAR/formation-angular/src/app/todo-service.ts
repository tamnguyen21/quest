import { Injectable } from '@angular/core';
import { Todo } from './todo';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom, map, Observable, startWith, Subject, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private apiUrl: string = "https://jsonplaceholder.typicode.com/todos";
  private todos!: Todo[];
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  // findAll qui retourne un Observable : option recommandée
  public findAll(): Observable<Todo[]> {
    // return this.http.get<Todo[]>(this.apiUrl);

    // Créer un Observable à partir du Subject
    return this.refresh$.pipe( // permet de transformer un flux / manipuler un flux
      // Forcer un premier chargement
      startWith(null),

      // Transformer le "void" que Todo[] en allant chercher les informations
      switchMap(() => {
        return this.http.get<Todo[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  // findAll qui retourne un tableau, SANS changer de référence
  public findAllSubscribe(): Todo[] {
    this.todos = []; // réference #1

    // La méthode qui est dans le subscribe s'exécutera bien plus tard
    // > C'est ce qu'on appelle de l'asynchrone
    this.http.get<Todo[]>(this.apiUrl).subscribe(resp => {
      // Changement de la référence -> référence #2
      // this.todos = resp;

      // Il faut dans ce cas pousser chaque élément dans le tableau, et pour aller plus vite, on peut déconstruire le tableau
      // Réference #1
      this.todos.push(...resp);
    });

    // Le retour s'exécute bien avant le subscribe
    // Donc, return this.todos -> référence #1
    return this.todos;
  }

  // findAll qui retourne une Promesse
  public async findAllPromise(): Promise<Todo[]> {
    // "await" nous permet d'attendre la réponse
    // pour que le "await" fonctionne, il faut être dans une méthode "async"
    // "firstValueFrom" transforme l'Observable en Promise
    this.todos = await firstValueFrom(this.http.get<Todo[]>(this.apiUrl));

    // Grâce au "await", le return se fera après la réception de la réponse HTTP
    return this.todos;
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

  public save(todo: Todo): void {
    const payload = {
      id: todo.id,
      title: todo.title,
      completed: todo.completed,
      userId: todo.userId
    };

    if (!todo.id) {
      this.http.post<Todo>(this.apiUrl, payload).subscribe(() => this.refresh());
    }

    this.http.put<Todo>(`${ this.apiUrl }/${ todo.id }`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());
  }
}
