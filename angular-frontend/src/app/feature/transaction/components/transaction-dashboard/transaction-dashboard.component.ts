import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-transaction-dashboard',
  imports: [CommonModule],
  templateUrl: './transaction-dashboard.component.html',
  styleUrl: './transaction-dashboard.component.scss'
})
export class TransactionDashboardComponent {

  transactions = [
    {
      date: '2025-06-24',
      amount: 150.75,
      type: 'Income',
      notes: 'Salary'
    },
    {
      date: '2025-06-22',
      amount: 45.50,
      type: 'Expense',
      notes: 'Groceries'
    }
  ];

}
