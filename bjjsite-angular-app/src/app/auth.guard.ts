import { CanActivate, CanActivateFn, Router } from '@angular/router';
import { AuthenticationService } from './features/loginpage/authentication.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthenticationService);
  const router = inject(Router);

  const token = localStorage.getItem('authToken');
  if (token && authService.isTokenValid(token)) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
