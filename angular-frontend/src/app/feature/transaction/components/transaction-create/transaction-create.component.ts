import { Component, OnInit } from '@angular/core';
import { TransactionCreate } from '../../model/transaction-create';
import { BankInput } from '../../../bank/model/bank-input';
import { TransactionService } from '../../service/transaction.service';
import { BankService } from '../../../bank/service/bank.service';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';

@Component({
  selector: 'app-transaction-create',
  imports: [FormsModule, NgSelectModule],
  templateUrl: './transaction-create.component.html',
  styleUrl: './transaction-create.component.scss'
})
export class TransactionCreateComponent implements OnInit {

  transaction = new TransactionCreate();
  transactionTypes: string[] = []; // Will be populated with actual types later
  banks: BankInput[] = []; // Will be populated with actual banks later

  constructor(
    private transactionService: TransactionService,
    private bankService: BankService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadTransactionTypes();
    this.loadBanks();
  }

  // Method called when the form is submitted
  onSubmit() {
    console.log('Transaction submitted:', this.transaction);
    this.saveTransaction();
  }

  goBack() {
    this.router.navigate(['/transaction-dashboard']);
  }

  // Method to save the transaction
  private async saveTransaction() {
    try {
      const data = await firstValueFrom(this.transactionService.createTransaction(this.transaction));
      console.log('Transaction created:', data);
      this.goToTransactionList();
    } catch (error) {
      console.error('Error creating transaction:', error);
    }
  }

  // Navigate to transaction list
  private goToTransactionList() {
    this.router.navigate(['/money-transactions']);
  }

  private loadTransactionTypes(): void {
    this.transactionService.getTransactionTypes().subscribe({
      next: (types: string[]) => {
        this.transactionTypes = types;
      },
      error: (err) => {
        console.error('Error loading transaction types', err);
      }
    });
  }

  private loadBanks(): void {
    this.bankService.getBanksForTransactionCreate().subscribe({
      next: (banks: BankInput[]) => {
        this.banks = banks;
      },
      error: (err) => {
        console.error('Error loading banks', err);
      }
    });
  }
}
