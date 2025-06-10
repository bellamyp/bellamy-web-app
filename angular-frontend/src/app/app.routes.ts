import { Routes } from '@angular/router';

export const routes: Routes = [
    { path: '', redirectTo: '/main-menu', pathMatch: 'full' },
    {
        path: 'main-menu',
        loadComponent: () => import('./feature/menu/components/main-menu/main-menu.component')
            .then(m => m.MainMenuComponent)
    },
    {
        path: 'user-login',
        loadComponent: () => import('./feature/user/components/login/login.component')
            .then(m => m.LoginComponent)
    }
];
