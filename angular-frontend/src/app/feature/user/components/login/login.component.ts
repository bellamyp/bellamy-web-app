import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../../../../core/service/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {}

  onLogin(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        this.authService.storeToken(response.token);

        // Debug only
        // console.log('Login successful, token stored:', response.token);

        this.router.navigate(['/main-menu']).then(success => {
          console.log('Navigation to main menu success:', success);
        }).catch(err => {
          console.error('Navigation to main menu error:', err);
        });
      },
      error: (err) => {
        this.errorMessage = 'Invalid username or password';
        console.error('Login failed:', err);
        this.password = '';  // clear password field
      }
    });
  }

  onRegister(): void {
    // Navigate to the user registration page
    this.router.navigate(['/user-registration']);
  }
}
