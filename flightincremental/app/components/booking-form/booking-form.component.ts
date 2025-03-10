import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Booking } from 'src/app/models/booking.model';
import { Flight } from 'src/app/models/flight.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/service/auth.service';
import { BookingService } from 'src/app/service/booking.service';
import { FlightService } from 'src/app/service/flight.service';

@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent implements OnInit {
  bookingForm: FormGroup;
  totalCost:number;
  errorMessage:string;
  bookings: Booking[];
  flight: Flight = {
    flightNumber: '',
    airline: '',
    departureLocation: '',
    arrivalLocation: '',
    departureTime: '',
    arrivalTime: '',
    price: 0,
    totalSeats: 0
  };
  user: User = {
    password: '',
    username: '',
    email: '',
    mobileNumber: '',
    userRole: ''
  }
  constructor(private bookingService: BookingService,
    private flightService: FlightService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private formBuilder : FormBuilder,private authService:AuthService
  ){

    this.bookingForm = formBuilder.group(
      {
        bookingDate: ['', [Validators.required]],
        numberOfPassengers: [1, [Validators.required,Validators.min(1)]],
        flightNumber: ['', [Validators.required]]
      }
    );
  }

  ngOnInit(): void {
    this.loadBookings();
    let flightId: number = +this.activatedRoute.snapshot.paramMap.get('id');
    this.flightService.getFlightById(flightId).subscribe((data) => {
      this.flight = data;
      this.bookingForm.setValue({
        flightNumber: this.flight.flightNumber,
        bookingDate: '',
        numberOfPassengers:1
      })
    });
    const username:string = localStorage.getItem('username');
    this.authService.getUserByUsername(username).subscribe(
      (data)=>{
        this.user=data;
      },
      (error)=>{this.errorMessage=error.error; this.router.navigate(['/login'])}
    )
    this.bookingForm.get('numberOfPassengers').valueChanges.subscribe(value=>this.calculateTotalPrice());
  }
  addNewBooking(): void {
    if(this.bookingForm.valid){
      this.totalCost = this.bookingForm.value.numberOfPassengers*this.flight.price;
      const newBooking:Booking = {
        flight : this.flight,
        user : this.user,
        bookingDate:this.bookingForm.value.bookingDate,
        numberOfPassengers : this.bookingForm.value.numberOfPassengers,
        status: false
      };
      this.bookingService.createBooking(newBooking).subscribe(
        ()=>{
        this.bookingForm.reset();
        },
        (error)=>{this.errorMessage=error.error}
      );
    }
  }
  loadBookings(): void {
    this.bookingService.getBookings().subscribe((result) => {
      this.bookings = result;
    })
  }
  get f(){
    return this.bookingForm.controls;
  }
  calculateTotalPrice():void{
    this.totalCost = this.flight.price*this.bookingForm.value.numberOfPassengers;
  }
}
