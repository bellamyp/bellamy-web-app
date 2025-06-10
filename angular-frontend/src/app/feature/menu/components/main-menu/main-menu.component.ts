import { Component } from '@angular/core';

@Component({
  selector: 'app-main-menu',
  imports: [],
  templateUrl: './main-menu.component.html',
  styleUrl: './main-menu.component.scss'
})
export class MainMenuComponent {
  logIn(): void {
    // Navigate to the user log in page
    window.location.href = '/user-login';
  }
}
