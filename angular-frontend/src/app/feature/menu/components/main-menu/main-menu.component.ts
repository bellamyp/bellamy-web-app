import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../../../core/service/authentication.service';

@Component({
  selector: 'app-main-menu',
  imports: [],
  templateUrl: './main-menu.component.html',
  styleUrl: './main-menu.component.scss'
})
export class MainMenuComponent {

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  userRegistration(): void {
    // Navigate to the user registration page
    this.router.navigate(['/user-registration']);
  }

  transactionDashboard(): void {
    // Navigate to the transaction dashboard page
    alert('Transaction Dashboard is under construction.');
  }

  bankDashboard(): void {
    // Navigate to the bank dashboard page
    this.router.navigate(['/bank-dashboard']);
  }

  tickTacToe(): void {
    // Navigate to the Tic Tac Toe game page
    this.router.navigate(['/tic-tac-toe']);
  }

  changeBackground(): void {
    // Navigate to the background change page
    alert('Change Background feature is under construction.');
  }

  sendEmail(): void {
    const username = this.authenticationService.username();
    const subject = `Bellamy Web App: Username '${username ?? 'Guest'}' request support`;
    const body = `Please provide details about the issues or requests you have regarding the Bellamy Web App below:\n\n`;
    const mailtoLink = `mailto:bellamyphan@icloud.com
      ?subject=${encodeURIComponent(subject)}
      &body=${encodeURIComponent(body)}`;
    window.location.href = mailtoLink;
  }

  manageUsers(): void {
    // Navigate to the user management page
    alert('User management is under construction.\nOnly admin can access this feature.');
  }

}
