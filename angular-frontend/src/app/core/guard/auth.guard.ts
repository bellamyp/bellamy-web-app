import { CanActivateFn, Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { inject } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

export const authGuard: CanActivateFn = (route, state) => {
  
  const authService = inject(AuthenticationService);
  const router = inject(Router);
  const token = authService.getToken();
  // console.log('AuthGuard: Token found:', token); // Debugging log

  if (token) {
    try {
      const decodedToken: any = jwtDecode(token); // Decode the token
      const isExpired = decodedToken.exp * 1000 < Date.now(); // Check if the token is expired

      if (isExpired) {
        const email = decodedToken.sub; // Assuming the token contains a subject field
        console.warn('AuthGuard: Token is expired');
        alert(`User: ${email}, you have been logged out due expired session. Please log in again.`); // User-specific alert
        authService.logout(); // Logout the user
        router.navigate(['/login']); // Navigate to login if expired
        return false;
      }

      return true; // Token is valid
    } catch (error) {
      console.error('AuthGuard: Invalid token', error);
      alert('Your session is invalid. Please log in again.'); // Show alert message for invalid token
      router.navigate(['/login']); // Navigate to login if token is invalid
      return false;
    }
  } else {
    console.warn('AuthGuard: No token found');
    alert('You are not logged in. Please log in to continue.'); // Show alert message for no token
    router.navigate(['/user-login']); // Navigate to login if no token
    return false;
  }
};