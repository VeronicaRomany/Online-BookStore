import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CheckOutComponent } from '../popUp/check-out/check-out.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(public dialog:MatDialog) { }

  ngOnInit(): void {
  }
 checkOut(){
  this.dialog.open(CheckOutComponent,{data:{bookId:6 }});
 }
}
