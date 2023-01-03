import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';

const routes: Routes = [
  
  {path:"Home" , component:HomepageComponent},
  {path:"Login" , component:LoginComponent},
  {path:"Edit" , component:EditProfileComponent},

  {path:"**" ,redirectTo:"Home",pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
