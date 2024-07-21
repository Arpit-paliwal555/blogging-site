import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { DetailsComponent } from './details/details.component';
import { CreateblogComponent } from './createblog/createblog.component';

export const routes: Routes = [
    {path: '', redirectTo: 'signin', pathMatch: 'full'},
    {path: 'home',component:HomeComponent},
    {path: 'signin', component:SigninComponent},
    {path: 'signup', component:SignupComponent},
    {path: 'details/:blogId', component:DetailsComponent},
    {path: 'createblog', component:CreateblogComponent},
    {path: '**', redirectTo: 'signin', pathMatch: 'full'}
];
