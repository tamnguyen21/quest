import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  imports: [],
  templateUrl: './navigation.html',
  styleUrl: './navigation.css',
})
export class Navigation implements OnInit {
  ngOnInit(): void {
    console.log("Initialisation de la navigation !");
  }

  @Input() utilisateur: string = "";
}
