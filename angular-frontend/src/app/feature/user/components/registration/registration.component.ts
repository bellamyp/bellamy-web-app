import { Component, signal } from '@angular/core';
import { UserRegistration } from '../../model/user-registration';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-registration',
  imports: [FormsModule],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.scss'
})
export class RegistrationComponent {

  user = signal<UserRegistration>(new UserRegistration());

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  onRegister(): void {

    const currentUser = this.user();
    const { password, ...safeUser } = currentUser;

    console.log('User Registration Details (without password):', safeUser);

    this.userService.registerUser(currentUser).subscribe({
      next: (response) => {
        console.log('User registered successfully:', response);
        alert(`User registered successfully!
          \nFirst Name: ${currentUser.firstName}
          \nLast Name: ${currentUser.lastName}
          \nUsername: ${currentUser.username}`);
        this.router.navigate(['/user-login']).catch(console.error);
      },
      error: (error) => {
        console.error('Error registering user:', error);
        alert('Error registering user. Please try again.');
      }
    });
  }

  private updateField(field: keyof UserRegistration, value: string) {
    this.user.update((u) => ({ ...u, [field]: value }));
  }

  onInputChange(field: keyof UserRegistration, event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input) {
      this.updateField(field, input.value);
    }
  }
}
