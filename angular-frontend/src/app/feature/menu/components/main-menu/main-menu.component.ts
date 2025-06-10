import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-menu',
  imports: [],
  templateUrl: './main-menu.component.html',
  styleUrl: './main-menu.component.scss'
})
export class MainMenuComponent {

  constructor(private router: Router) {}

  logIn(): void {
    // Navigate to the user log in page
    this.router.navigate(['/user-login']);
  }
}
