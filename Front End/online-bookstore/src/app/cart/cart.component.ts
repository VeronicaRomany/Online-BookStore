import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CheckOutComponent } from '../popUp/check-out/check-out.component';
import { Book } from '../shared/Book';
import { obj } from '../shared/obj';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
    
    cart:obj[]=[]
  constructor(public dialog:MatDialog) { }

  ngOnInit(): void {
    if(localStorage.getItem("cart")!=null)
        {
        this.cart= JSON.parse(localStorage.getItem("cart")!)
        
        }
  }
 checkOut(){
 

  
  this.dialog.open(CheckOutComponent,{data:{cart:this.cart }});
 }
 isEmpty(){
  return this.cart.length==0
 }
 remove(){
  if(localStorage.getItem("cart")!=null)
        {
        this.cart= JSON.parse(localStorage.getItem("cart")!)
        this.cart.pop()
        localStorage.setItem("cart",JSON.stringify(this.cart))
        }
 }
}
