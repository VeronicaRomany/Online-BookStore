import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { obj } from 'src/app/shared/obj';
import { Credit, OrderRequest } from './orderRequest';

@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css']
})
export class CheckOutComponent implements OnInit {
 cart:obj[]=[]
 amount:number=0
 cost:number=0
  constructor(@Inject(MAT_DIALOG_DATA) public data:{cart:obj[]},private dialogRef: MatDialogRef<CheckOutComponent>) { }

  ngOnInit(): void {
    this.cart=this.data.cart
    
    for(let i=0 ;i<this.cart.length;i++){
      this.amount+=this.cart[i].amount
      this.cost+= this.cart[i].amount * this.cart[i].book.price
    }
  }
 

submit(){
  let orders: Map<string, number> = new Map();
  let ord : OrderRequest =new OrderRequest()
  let credit: Credit =new Credit()
  for(let i=0 ;i<this.cart.length;i++){
      orders.set(this.cart[i].book.ISBN,this.cart[i].amount)
  }
  var creditNum = document.getElementById("cardNum") as HTMLInputElement
  var year = document.getElementById("year") as HTMLSelectElement
 
  credit.number=creditNum.value
  credit.expiryDate=year.value
  ord.orders=orders
  ord.creditCard=credit

  //request
  
  console.log(ord);
 
  

  localStorage.removeItem("cart");
  this.dialogRef.close()
  window.location.reload()
}
}
