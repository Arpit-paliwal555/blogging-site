import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-createblog',
  standalone: true,
  imports: [ HeaderComponent],
  templateUrl: './createblog.component.html',
  styleUrl: './createblog.component.css'
})
export class CreateblogComponent {
    
}
