import { Routes } from '@angular/router';
import { authGuard } from './core/guard/auth.guard';

export const routes: Routes = [
    { path: '', redirectTo: '/main-menu', pathMatch: 'full' },
    {
        path: 'main-menu',
        loadComponent: () => import('./feature/menu/components/main-menu/main-menu.component')
            .then(m => m.MainMenuComponent)
    },
    {
        path: 'admin-menu',
        canActivate: [authGuard],
        loadComponent: () => import('./feature/menu/components/admin-menu/admin-menu.component')
            .then(m => m.AdminMenuComponent)
    },
    {
        path: 'user-login',
        loadComponent: () => import('./feature/user/components/login/login.component')
            .then(m => m.LoginComponent)
    },
    {
        path: 'user-registration',
        loadComponent: () => import('./feature/user/components/registration/registration.component')
            .then(m => m.RegistrationComponent)
    },
    {
        path: 'transaction-dashboard',
        canActivate: [authGuard],
        loadComponent: () => import('./feature/transaction/components/transaction-dashboard/transaction-dashboard.component')
            .then(m => m.TransactionDashboardComponent)
    },
    {
        path: 'bank-dashboard',
        canActivate: [authGuard],
        loadComponent: () => import('./feature/bank/components/dashboard/dashboard.component')
            .then(m => m.DashboardComponent)
    },
    {
        path: 'bank-create',
        canActivate: [authGuard],
        loadComponent: () => import('./feature/bank/components/bank-create/bank-create.component')
            .then(m => m.BankCreateComponent)
    },
    {
        path: 'tic-tac-toe',
        loadComponent: () => import('./feature/tic-tac-toe/components/board/board.component')
            .then(m => m.BoardComponent)
    }
];
