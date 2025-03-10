import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight } from '../models/flight.model';

@Injectable({
  providedIn: 'root'
})
export class FlightService {
  public baseUrl:string = 'https://8080-cbfadfbbdaa322263798edccecfbdeone.premiumproject.examly.io/api/flights';
  constructor(private http:HttpClient) { }

  getFlights():Observable<Flight[]> {
    return this.http.get<Flight[]>(this.baseUrl);
  }
  createFlight(flight:Flight):Observable<Flight>{
    return this.http.post<Flight>(this.baseUrl,flight);
  }
  updateFlight(flight:Flight):Observable<Flight>{
    return this.http.put<Flight>(`${this.baseUrl}/${flight.flightId}`,flight);
  }
  deleteFlight(id:number):Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  getFlightById(id:number):Observable<Flight>{
    return this.http.get<Flight>(`${this.baseUrl}/${id}`);
  }
}
