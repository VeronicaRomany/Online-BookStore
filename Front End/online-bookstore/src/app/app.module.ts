import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeadersComponent } from './shared/headers/headers.component';

import {MatTabsModule} from '@angular/material/tabs';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';

import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './registeration/register/register.component';
import { LoginComponent } from './login/login.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { NewBookComponent } from './new-book/new-book/new-book.component';
import { CartComponent } from './cart/cart.component';
import { AddCartComponent } from './popUp/add-cart/add-cart.component';
import {ProfileComponent} from "./profile/profile.component";
import { CheckOutComponent } from './popUp/check-out/check-out.component';
import {SearchUsersComponent} from "./searchUsers/searchUsers.component";




@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomepageComponent,
    HeadersComponent,
    EditProfileComponent,
    NewBookComponent,
    CartComponent,
    AddCartComponent,
    ProfileComponent,
    SearchUsersComponent,
    CheckOutComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
