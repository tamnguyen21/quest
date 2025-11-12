import { Component, Input, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navigation',
  imports: [ RouterModule ],
  templateUrl: './navigation.html',
  styleUrl: './navigation.css',
})
export class Navigation implements OnInit {
  ngOnInit(): void {
    console.log("Initialisation de la navigation !");
  }

  @Input() utilisateur: string = "";
}
