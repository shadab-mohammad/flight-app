import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/models/booking.model';
import { BookingService } from 'src/app/service/booking.service';

@Component({
  selector: 'app-manage-booking',
  templateUrl: './manage-booking.component.html',
  styleUrls: ['./manage-booking.component.css']
})
export class ManageBookingComponent implements OnInit {
  bookings: Booking[] = []
  errorMessage: string;
  constructor(private bookingService: BookingService) { }

  ngOnInit(): void {
    this.loadBookings();
  }
  loadBookings(): void {
    this.bookingService.getBookings().subscribe((result) => {
      this.bookings = result;
    })
  }
  updateBookingStatus(bookingId: number,status:boolean): void {
    this.bookingService.updateBookingStatus(bookingId,status).subscribe(
      (result)=>this.loadBookings(),
      (error)=>alert(error.error)
    );
  }

}
