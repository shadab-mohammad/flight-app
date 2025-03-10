import { Component } from '@angular/core';
import { HomeComponent } from "./components/home/home.component";
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(){}
  title = 'angularapp';
  
}
