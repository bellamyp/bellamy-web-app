import { Component, OnInit } from '@angular/core';
import { Transaction } from '../../model/transaction';
import { TransactionService } from '../../service/transaction.service';
import { TransactionTypeService } from '../../service/transaction-type.service'; // Import the service
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { TransactionType } from '../../model/transaction';
import { Bank } from '../../model/bank';
import { BankService } from '../../service/bank.service';

@Component({
  selector: 'app-transaction-create',
  standalone: false,
  templateUrl: './transaction-create.component.html',
  styleUrls: ['./transaction-create.component.css']
})
export class TransactionCreateComponent implements OnInit {

  transaction: Transaction = new Transaction();
  transactionTypes: TransactionType[] = []; // Array to store transaction types
  banks: Bank[] = []; // Array to store banks

  constructor(
    private transactionService: TransactionService,
    private transactionTypeService: TransactionTypeService,
    private bankService: BankService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loadTransactionTypes();
    this.loadBanks();
  }

  // Load transaction types from the service
  loadTransactionTypes() {
    this.transactionTypeService.getTransactionTypes().subscribe(
      (types: TransactionType[]) => {
        console.log('Fetched transaction types:', types); // Print fetched types
        this.transactionTypes = types;
        console.log('Assigned transaction types:', this.transactionTypes); // Print assigned transaction types
      }, (error) => {
        console.error('Error fetching transaction types:', error);
      }
    );
  }

  // Load banks from the service
  loadBanks() {
    this.bankService.getBanks().subscribe(
      (banks: Bank[]) => {
        console.log('Fetched banks:', banks)
        this.banks = banks;
        console.log('Assigned banks:', this.banks);
      }, (error) => {
        console.error('Error fetching banks:', error)
      }
    )
  }

  // Method to save the transaction
  async saveTransaction() {
    try {
      const data = await firstValueFrom(this.transactionService.createTransaction(this.transaction));
      console.log('Transaction created:', data);
      this.goToTransactionList();
    } catch (error) {
      console.error('Error creating transaction:', error);
    }
  }

  // Navigate to transaction list
  goToTransactionList() {
    this.router.navigate(['/money-transactions']);
  }

  goBack() {
    this.router.navigate(['/money-transactions']);
  }

  // Method called when the form is submitted
  onSubmit() {
    console.log('Transaction submitted:', this.transaction);
    this.saveTransaction();
  }
}
