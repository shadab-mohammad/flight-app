import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UserToken } from 'src/app/models/usertoken.model';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string='';
  password: string='';
  errorMessage:string;
  constructor(private authService:AuthService,private router:Router) { }

  ngOnInit(): void {
  }
  login():void{
    let user:User = {
      username:this.username,
      password:this.password
    }
    this.authService.login(user).subscribe((userToken)=>{
      console.log(userToken.token)
      localStorage.setItem('token',userToken.token);
      localStorage.setItem('userId',userToken.userId+'');
      localStorage.setItem('username',userToken.username);
      localStorage.setItem('userRole',userToken.userRole);
      alert("login success");
      this.router.navigate(['/']);
    },
    (error)=>{alert(error.error)}
    )
  }

}
