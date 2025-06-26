import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BankInput } from '../../model/bank-input';
import { BankService } from '../../service/bank.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {

  banks: BankInput[] = []; // This will be populated with bank data

  constructor(
    private router: Router,
    private bankService: BankService
  ) { }

  ngOnInit(): void {
    this.getBankData();
  }

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

  private getBankData(): void {
    this.bankService.getBanksForTransactionCreate().subscribe({
      next: (data: BankInput[]) => {
        this.banks = data;
      },
      error: (err) => {
        console.error('Error loading banks', err);
      }
    });
  }
}
