import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-menu',
  imports: [],
  templateUrl: './admin-menu.component.html',
  styleUrl: './admin-menu.component.scss'
})
export class AdminMenuComponent {

  manageUsers(): void {
    alert('Manage Users functionality is not implemented yet.');
  }

  importData(): void {
    alert('Import Data functionality is not implemented yet.');
  }

  exportData(): void {
    alert('Export Data functionality is not implemented yet.');
  }

  servicesAndRoles(): void {
    alert('Services and Roles functionality is not implemented yet.');
  }

  manageBackground(): void {
    alert('Manage Background functionality is not implemented yet.');
  }

  monitorUserActivities(): void {
    alert('Monitor User Activities functionality is not implemented yet.');
  }

  supportDashboard(): void {
    alert('Support Dashboard functionality is not implemented yet.');
  }
}
