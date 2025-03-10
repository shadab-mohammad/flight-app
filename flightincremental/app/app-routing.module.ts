import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AddFlightComponent } from './components/add-flight/add-flight.component';
import { FlightListComponent } from './components/flight-list/flight-list.component';
import { ManageBookingComponent } from './components/manage-booking/manage-booking.component';
import { MyHistoryComponent } from './components/my-history/my-history.component';
import { BookingFormComponent } from './components/booking-form/booking-form.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdminGuard } from './service/admin.guard';
import { UserGuard } from './service/user.guard';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'add-flight/:id',component:AddFlightComponent,canActivate:[AdminGuard]},
  {path:'add-flight',component:AddFlightComponent , canActivate:[AdminGuard]},
  {path:'flight-list',component:FlightListComponent},
  {path:'manage-booking',component:ManageBookingComponent,canActivate:[AdminGuard]},
  {path:'book-form',component:BookingFormComponent , canActivate:[UserGuard]},
  {path:'book-form/:id',component:BookingFormComponent , canActivate:[UserGuard]},
  {path:'my-history',component:MyHistoryComponent, canActivate:[UserGuard]},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
