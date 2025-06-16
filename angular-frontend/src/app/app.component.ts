import { Component, inject } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { AuthenticationService } from './core/service/authentication.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'angular-frontend';

  private router = inject(Router);
  private authService = inject(AuthenticationService);

  // Create a computed signal to read username string or null for template use
  username = this.authService.username;

  // username is a computed signal in your service, so call as a function to get value
  toggleLoginLogout(): void {
    if (this.authService.username()) {
      this.authService.logout();
      alert('You have been logged out successfully!');
      this.router.navigate(['/main-menu']);
    } else {
      this.router.navigate(['/user-login']);
    }
  }
}
