import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BankCreate } from '../../model/bank-create';
import { BankService } from '../../service/bank.service';

@Component({
  selector: 'app-bank-create',
  imports: [FormsModule],
  templateUrl: './bank-create.component.html',
  styleUrl: './bank-create.component.scss'
})
export class BankCreateComponent implements OnInit {

  bank: BankCreate = {
    name: '',
    type: '',
    openingDate: new Date(),
  }

  // bankTypes signal, initially empty array
  bankTypes = signal<string[]>([]);

  constructor(
    private bankService: BankService
  ) { }

  ngOnInit(): void {
    this.loadBankTypes();
  }

  onSubmit(): void {
    // Handle form submission logic here
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

}
