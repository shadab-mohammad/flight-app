import { Flight } from "./flight.model";
import { User } from "./user.model";

export interface Booking {
    bookingId?:number;
    bookingDate?:Date;
    numberOfPassengers?:number;
    totalCost?:number;
    status?:boolean;
    flight?:Flight;
    user?:User;
}
