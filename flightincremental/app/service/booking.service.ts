import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../models/booking.model';
@Injectable({
  providedIn: 'root'
})
export class BookingService {
  
  public baseUrl:string = 'https://8080-cbfadfbbdaa322263798edccecfbdeone.premiumproject.examly.io/api/bookings';
  constructor(private http:HttpClient) { }

  getBookings():Observable<Booking[]> {
    return this.http.get<Booking[]>(this.baseUrl);
  }
  createBooking(booking:Booking):Observable<Booking>{
    return this.http.post<Booking>(this.baseUrl,booking);
  }
  updateBooking(booking:Booking):Observable<Booking>{
    return this.http.put<Booking>(`${this.baseUrl}/${booking.bookingId}`,booking);
  }
  getBookingById(id:number):Observable<Booking>{
    return this.http.get<Booking>(`${this.baseUrl}/${id}`);
  }
  deleteBooking(id:number):Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  getBookingsByUserId(userId:number):Observable<Booking[]>{
    return this.http.get<Booking[]>(`${this.baseUrl}/user/${userId}`);
  }
  updateBookingStatus(bookingId: number, status: boolean):Observable<any> {
    const payload = {status:status};
    return this.http.patch<any>(`${this.baseUrl}/${bookingId}`,payload);
  }
}
