import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { UserToken } from '../models/usertoken.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private apiUrl:string = 'https://8080-cbfadfbbdaa322263798edccecfbdeone.premiumproject.examly.io/api';
  constructor(private http:HttpClient) { }

  login(user:User):Observable<UserToken>{
    return this.http.post<UserToken>(`${this.apiUrl}/login`,user);
  }
  register(user:User):Observable<User>{
    return this.http.post<User>(`${this.apiUrl}/register`,user);
  }
  getUsers():Observable<User[]>{
    return this.http.get<User[]>(`${this.apiUrl}/users`);
  }
  getUserById(userId:number):Observable<User>{
    return this.http.get<User>(`${this.apiUrl}/user/${userId}`);
  }
  getUserByUsername(username:string):Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/user`,{params:{ username }});
  }

  isAdmin():boolean{
    let role = localStorage.getItem('userRole');
    return role=='ADMIN'?true:false;
  }
  isUser():boolean{
    let role = localStorage.getItem('userRole');
    return role=='USER'?true:false;
  }
  getUserRole():string{
    return localStorage.getItem('userRole');
  }
  getUsername():string{
    return localStorage.getItem('username');
  }
  getUserId():string{
    return localStorage.getItem('userId');
  }
  logout():void {
    localStorage.removeItem('username');
    localStorage.removeItem('userRole');
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
  }
  isLoggedIn():boolean {
    const user:string = localStorage.getItem('username');
    return user?true:false;
  }
  
}
