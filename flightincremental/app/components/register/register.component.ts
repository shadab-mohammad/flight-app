import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registrationSuccess:boolean=false;
  errorMessage:string;
  constructor(private authService:AuthService,private router:Router) { }

  ngOnInit(): void {
  }
  register(form:NgForm):void {
    if(form.valid){
      this.authService.register(form.value).subscribe(()=>{
        this.registrationSuccess=true;
      },
      (error)=>{
        this.errorMessage = error.error;
      })
    }
  }
  redirectToLogin():void{
    this.router.navigate(['/login']);
  }
}
