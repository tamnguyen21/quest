import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth-service';

export const jwtHeaderInterceptor: HttpInterceptorFn = (req, next) => {
  // Injecter le service AuthService
  const authService = inject(AuthService);

  console.log("INTERCEPTOR JWT HEADER");

  const authReq = req.clone({
    // "headers" : on modifie TOUTES les en-têtes
    // "setHeaders" : on ajoute / modifie UNE ou plusieurs en-têtes
    setHeaders: {
      'Authorization': "Bearer " + authService.token
    }
  });

  return next(authReq);
};
