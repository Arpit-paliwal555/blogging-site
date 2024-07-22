import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css'
})
export class SigninComponent {
  username:String = '';
  password:String = '';
  errorMessage:String = '';

  constructor(private authService: AuthService, private router: Router) {

  }
  

  login() {
    if(this.username.trim().length==0 && this.password.trim().length==0){
        this.errorMessage = 'Please enter username and password';
        return;
    }
    if(this.username.trim().length===0){
        this.errorMessage = 'Username cannot be empty';
        return;
    }
    if(this.password.trim().length===0){
        this.errorMessage = 'Password cannot be empty';
        return;
    }
    this.errorMessage = '';
    this.authService.authorize( this.username, this.password ).subscribe(response => {
      console.log('Logged in successfully', response);
      this.router.navigate(['/home']);
      // Handle post-login actions
    }, error => {
      this.errorMessage = 'Invalid username or password';
      console.error('Login failed', error);
    });
  
  }

}
