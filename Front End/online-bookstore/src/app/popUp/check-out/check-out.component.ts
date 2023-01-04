import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { obj } from 'src/app/shared/obj';

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
      this.cost+= this.cart[i].amount * this.cart[i].book.sellingPrice
    }
  }
 

submit(){
  localStorage.removeItem("cart");
  this.dialogRef.close()
  window.location.reload()
}
}
