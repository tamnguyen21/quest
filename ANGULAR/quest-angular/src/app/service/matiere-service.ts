import { Injectable } from '@angular/core';
import { MatiereDto } from '../dto/matiere-dto';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class MatiereService {
  private apiUrl: string = '/matiere';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<MatiereDto[]> {
    return this.refresh$.pipe( // permet de transformer un flux / manipuler un flux
      // Forcer un premier chargement
      startWith(null),

      // Transformer le "void" que MatiereDto[] en allant chercher les informations
      switchMap(() => {
        return this.http.get<MatiereDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  public findById(id: number): Observable<MatiereDto> {
    return this.http.get<MatiereDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(MatiereDto: MatiereDto): void {
    const payload = MatiereDto.toJson();

    if (!MatiereDto.id) {
      this.http.post<MatiereDto>(this.apiUrl, payload).subscribe(() => this.refresh());
    }

    this.http.put<MatiereDto>(`${ this.apiUrl }/${ MatiereDto.id }`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());
  }
}
