import { TestBed } from '@angular/core/testing';

import { FlightService } from './flight.service';
import { HttpClientModule } from '@angular/common/http';

describe('FlightService', () => {
  let service: FlightService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule]
    });
    service = TestBed.inject(FlightService);
  });

  fit('Frontend_day35_should create flight service', () => {
    expect(service).toBeTruthy();
  });
});
