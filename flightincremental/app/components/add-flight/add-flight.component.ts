import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Flight } from 'src/app/models/flight.model';
import { FlightService } from 'src/app/service/flight.service';

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.css']
})
export class AddFlightComponent implements OnInit {
  flight: Flight = {
    flightNumber: '',
    airline: '',
    departureLocation: '',
    arrivalLocation: '',
    departureTime: '',
    arrivalTime: '',
    price: 0,
    totalSeats: 0
  }
  successMessage: string;
  errorMessage: string;
  isEditing: boolean = false;
  constructor(private route: ActivatedRoute, private flightService: FlightService, private router: Router) { }

  ngOnInit(): void {
    let flightId = +this.route.snapshot.paramMap.get('id');
    console.log(flightId);
    if (flightId) {
      this.isEditing = true;
      this.loadFlight(flightId);
    }
  }
  loadFlight(id: number): void {
    this.flightService.getFlightById(id).subscribe(
      (data: Flight) => { this.flight = data },
      (error: any) => { this.errorMessage = error.error }
    );
  }
  addOrUpdateFlight(): void {
    if (this.isEditing) {
      this.flightService.updateFlight(this.flight).subscribe(
        (response: any) => {
          alert('Flight updated successfully');
        },
        (error: any) => {
          this.errorMessage = error.error;
        }
      );
    }
    else {
      this.flightService.createFlight(this.flight).subscribe(
        (response: any) => {
          alert("Flight added successfully")
        },
        (error: any) => {
          this.errorMessage = error.error;
        }
      );
    }
  }

  closeModal():void{
    this.router.navigate(['/flight-list']);
  }

}
