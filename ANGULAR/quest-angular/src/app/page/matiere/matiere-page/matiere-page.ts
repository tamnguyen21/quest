import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { MatiereDto } from '../../../dto/matiere-dto';
import { MatiereService } from '../../../service/matiere-service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-matiere-page',
  imports: [ CommonModule, RouterLink ],
  templateUrl: './matiere-page.html',
  styleUrl: './matiere-page.css',
})
export class MatierePage implements OnInit {
  protected matieres$!: Observable<MatiereDto[]>;

  constructor(private matiereService: MatiereService) { }

  ngOnInit(): void {
    this.matieres$ = this.matiereService.findAll();
  }

  public trackMatiere(index: number, value: MatiereDto) {
    return value.id;
  }
}
