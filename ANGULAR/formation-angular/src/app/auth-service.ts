import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = "THE_TOKEN";

  public get token(): string {
    return this._token;
  }
}
