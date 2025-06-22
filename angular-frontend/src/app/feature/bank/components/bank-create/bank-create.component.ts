import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BankCreate } from '../../model/bank-create';
import { BankService } from '../../service/bank.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bank-create',
  imports: [FormsModule, CommonModule],
  templateUrl: './bank-create.component.html',
  styleUrl: './bank-create.component.scss'
})
export class BankCreateComponent implements OnInit {

  bank = signal<BankCreate>({
    name: '',
    type: '',
    openingDate: new Date()
  });

  // bankTypes signal, initially empty array
  bankTypes = signal<string[]>([]);

  constructor(
    private router: Router,
    private bankService: BankService
  ) { }

  ngOnInit(): void {
    this.loadBankTypes();
  }

  onSubmit(): void {
    const currentBank = this.bank();
    this.bankService.createBank(currentBank).subscribe({
      next: (createdBank) => {
        alert(`Bank created successfully: ${createdBank.name}`);
        console.log('Created Bank:', createdBank);
        this.goBack();
      },
      error: (err) => {
        alert('Failed to create bank. Please try again.');
        console.error('Error creating bank:', err);
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/bank-dashboard']);
  }

  private loadBankTypes(): void {
    this.bankService.getBankTypes().subscribe({
      next: (types) => {
        this.bankTypes.set(types)

        // Debugging output to verify bank types
        console.log('BankTypes:', this.bankTypes());
      },
      error: (err) => console.error('Failed to load bank types', err)
    });
  }

  updateBankName(name: string) {
    this.bank.update(b => ({ ...b, name }));
  }

  updateOpeningDate(dateString: string) {
    this.bank.update(b => ({ ...b, openingDate: new Date(dateString) }));
  }

  updateBankType(type: string) {
    this.bank.update(b => ({ ...b, type }));
  }

}
