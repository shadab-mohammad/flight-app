import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Flight } from 'src/app/models/flight.model';
import { AuthService } from 'src/app/service/auth.service';
import { FlightService } from 'src/app/service/flight.service';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent implements OnInit {
  flights: Flight[] = []
  errorMessage:string;
  isAdmin:boolean=false;
  source:string;
  destination:string;
  constructor(private flightService:FlightService,private router:Router,private authService:AuthService) { }

  ngOnInit(): void {
    this.loadFlights();
  }
  loadFlights():void{
    this.flightService.getFlights().subscribe((result)=>{
      this.flights = result;
    })
    if(this.authService.isLoggedIn()){
      this.isAdmin = this.authService.isAdmin();
    }
  }
  updateFlight(id:number) :void {
    this.router.navigate(['/add-flight/',id]);
  }
  deleteFlight(id:number) :void {
    if(confirm('Confirm delete')){
      this.flightService.deleteFlight(id).subscribe(()=>{
        this.loadFlights();
      },(error)=>alert(error.error))
    }
  }
  navigateToBooking(id:number):void{
    this.router.navigate(['/book-form',id]);
  }
  
}
