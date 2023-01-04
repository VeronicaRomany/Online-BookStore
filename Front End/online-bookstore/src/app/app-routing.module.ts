import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './cart/cart.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { NewBookComponent } from './new-book/new-book/new-book.component';
import { RegisterComponent } from './registeration/register/register.component';
import {ProfileComponent} from "./profile/profile.component";
import {SearchUsersComponent} from "./searchUsers/searchUsers.component";

const routes: Routes = [

  {path:"Home" , component:HomepageComponent},
  {path:"Login" , component:LoginComponent},
  {path:"Edit" , component:EditProfileComponent},
  {path:"newBook" , component:NewBookComponent},
  {path:"Register" , component:RegisterComponent},
  {path:"Cart" , component:CartComponent},
  {path:"Profile" , component:ProfileComponent},
  {path:"SearchUsers" , component:SearchUsersComponent},
  {path:"**" ,redirectTo:"Home",pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
