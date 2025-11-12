import { Component } from '@angular/core';
import { AscBold } from '../asc-bold/asc-bold';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-home-page',
  imports: [
    AscBold
  ],
  templateUrl: './home-page.html',
  styleUrl: './home-page.css',
})
export class HomePage {
  constructor(private title: Title) {
    this.title.setTitle("Accueil");
  }
}
