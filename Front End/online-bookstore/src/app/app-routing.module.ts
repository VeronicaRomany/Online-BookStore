import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './cart/cart.component';
import { HomepageComponent } from './homepage/homepage.component';
import { NewBookComponent } from './new-book/new-book/new-book.component';
import { RegisterComponent } from './registeration/register/register.component';

const routes: Routes = [
  
  {path:"Home" , component:HomepageComponent},
  {path:"newBook" , component:NewBookComponent},
  {path:"Register" , component:RegisterComponent},
  {path:"Cart" , component:CartComponent},
  {path:"**" ,redirectTo:"Home",pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
