import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transaction-dashboard',
  imports: [CommonModule],
  templateUrl: './transaction-dashboard.component.html',
  styleUrl: './transaction-dashboard.component.scss'
})
export class TransactionDashboardComponent {

  transactions = [
    { date: '2025-06-24', amount: 150.75, type: 'Income', notes: 'Monthly income from Brookshires' },
    { date: '2025-06-22', amount: 45.50, type: 'Groceries', notes: 'Weekly grocery trip to Walmart' },
    { date: '2025-06-20', amount: 19.99, type: 'Meal', notes: 'Dinner at Panda Express with friends' },
    { date: '2025-06-18', amount: 75.00, type: 'Utility', notes: 'Electricity bill for June' },
    { date: '2025-06-16', amount: 12.50, type: 'Drink', notes: 'Coffee at Starbucks during study session' },
    { date: '2025-06-15', amount: 200.00, type: 'Income', notes: 'Freelance web design project payment' },
    { date: '2025-06-14', amount: 60.00, type: 'Transport', notes: 'Gas refill for the car' },
    { date: '2025-06-12', amount: 30.00, type: 'Subscription', notes: 'Monthly gym membership fee' },
    { date: '2025-06-10', amount: 89.99, type: 'Shopping', notes: 'New shoes from Nike online store' },
    { date: '2025-06-08', amount: 150.00, type: 'Rent', notes: 'Partial rent payment to roommate' },
    { date: '2025-06-06', amount: 8.75, type: 'Drink', notes: 'Smoothie at Tropical Smoothie Café' },
    { date: '2025-06-05', amount: 22.00, type: 'Meal', notes: 'Lunch with family at Chili’s' },
    { date: '2025-06-03', amount: 300.00, type: 'Income', notes: 'Side gig helping with a moving service' },
    { date: '2025-06-01', amount: 110.00, type: 'Utility', notes: 'Internet and cable bill for the month' },
    { date: '2025-05-30', amount: 14.25, type: 'Groceries', notes: 'Bought fruits and snacks for the week' }
  ];

  constructor(
    private router: Router
  ) { }

  addTransaction(): void {
    this.router.navigate(['/transaction-create']);
  }

  searchTransaction(): void {
    alert('Search Transaction functionality is not implemented yet.');
  }

  listTransactions(): void {
    alert('List Transactions functionality is not implemented yet.');
  }

  monthlyReport(): void {
    alert('Monthly Report functionality is not implemented yet.');
  }

  transactionEdit(): void {
    alert('Transaction Edit functionality is not implemented yet.');
  }

  transactionDelete(): void {
    alert('Transaction Delete functionality is not implemented yet.'); 
  }

}
