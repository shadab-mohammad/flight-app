import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/models/booking.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/service/auth.service';
import { BookingService } from 'src/app/service/booking.service';

@Component({
  selector: 'app-my-history',
  templateUrl: './my-history.component.html',
  styleUrls: ['./my-history.component.css']
})
export class MyHistoryComponent implements OnInit {
  bookings:Booking[]=[];
  
  userId:number = +localStorage.getItem('userId');
  constructor(private bookingService:BookingService,private authService:AuthService) { }
  ngOnInit(): void {
    this.loadUserBookings();
    console.log(this.bookings);
  }
  loadUserBookings():void{
    this.bookingService.getBookingsByUserId(this.userId).subscribe(
      (result)=>this.bookings=result,
      (error)=>alert(error.error)
    )

  }

}
