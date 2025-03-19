import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { User } from '../../../entities/User';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  user: User = new User('pedro', 'pedro@gmail.com', '12345');
  errorMessage: string = '';

  constructor() {}

  login() {
    console.log(this.user);
  }
}
