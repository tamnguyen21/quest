import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-observable-page',
  imports: [],
  templateUrl: './observable-page.html',
  styleUrl: './observable-page.css',
})
export class ObservablePage implements OnInit {

  protected simpleCounter$!: Observable<number>;

  ngOnInit(): void {
    let counter = 0;

    // Créer l'Observable et le stocker dans l'attribut simpleCounter$
    this.simpleCounter$ = new Observable<number>(obs => {
      // Simuler un nouvel élément toutes les secondes
      setInterval(() => {
        obs.next(counter++);
      }, 1_000);

      // Au bout de 10 secondes, on complète le flux
      setTimeout(() => {
        obs.complete();
      }, 10_000);
    });

    this.simpleCounter$.subscribe(val => {
      console.log(val);
    });
  }

}
