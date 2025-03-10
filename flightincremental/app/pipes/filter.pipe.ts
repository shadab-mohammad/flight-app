import { Pipe, PipeTransform } from '@angular/core';
import { Flight } from '../models/flight.model';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(flights:Flight[],source:string,destination:string): Flight[] {
    if(!source && !destination){
      return flights;
    }
    return flights.filter(flight =>
      (!source || flight.departureLocation.toLowerCase().includes(source.toLowerCase())) &&
      (!destination || flight.arrivalLocation.toLowerCase().includes(destination.toLowerCase()))
    );
  }

}
