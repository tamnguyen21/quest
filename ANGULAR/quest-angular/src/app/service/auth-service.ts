import { Injectable } from '@angular/core';
import { AuthRequestDto } from '../dto/auth-request-dto';
import { HttpClient } from '@angular/common/http';
import { AuthResponseDto } from '../dto/auth-response-dto';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = "";

  constructor(private http: HttpClient) {
    // On récupère le token de la sessionStorage, si ce token n'existe pas, on met un token vide
    this._token = sessionStorage.getItem("token") ?? "";
  }

  public get token(): string {
    return this._token;
  }

  public auth(authRequest: AuthRequestDto) {
    this.http.post<AuthResponseDto>('/auth', authRequest.toJson()).subscribe(resp => {
      this._token = resp.token;

      // Stocker le jeton dans le navigateur, dans le sessionStorage, avec la clé "token"
      sessionStorage.setItem("token", this._token);
    });
  }
}
