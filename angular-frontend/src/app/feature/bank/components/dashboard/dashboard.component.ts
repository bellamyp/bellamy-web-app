import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  constructor(
    private router: Router
  ) {}
  
  addBank(): void {
    // Navigate to the user registration page
    this.router.navigate(['/bank-create']);
  }

  updateBank(): void {
    alert('Update Bank is under construction.');
  }
  
  deleteBank(): void {
    alert('Delete Bank is under construction.');
  }

  bankDetails(): void {
    alert('Bank Details is under construction.');
  }

  bankStatement(): void {
    alert('Bank Statement is under construction.');
  }
}
