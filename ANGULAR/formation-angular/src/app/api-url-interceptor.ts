import { HttpInterceptorFn } from '@angular/common/http';

export const apiUrlInterceptor: HttpInterceptorFn = (req, next) => {

  console.log("INTERCEPTEUR URL !");

  // Toujours un clone de la requête qu'on veut altérer, et pas la requête directement
  const newReq = req.clone({
    url: 'https://jsonplaceholder.typicode.com' + req.url
  });

  // next permet de transférer la nouvelle requête
  return next(newReq);
};
