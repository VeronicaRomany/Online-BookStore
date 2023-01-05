import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GenericResponse } from 'src/app/shared/GenericResponse';
import { obj } from 'src/app/shared/obj';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
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
 inValidYear:boolean=false
 inValidCard:boolean=false
  constructor(@Inject(MAT_DIALOG_DATA) public data:{cart:obj[]},private dialogRef: MatDialogRef<CheckOutComponent>,private http:HttpClient,private token:TokenStorageService) { }

  ngOnInit(): void {
    this.cart=this.data.cart
    
    for(let i=0 ;i<this.cart.length;i++){
      this.amount+=this.cart[i].amount
      this.cost+= this.cart[i].amount * this.cart[i].book.price
    }
  }
 

submit(){
  var creditNum = document.getElementById("cardNum") as HTMLInputElement
  var year = document.getElementById("year") as HTMLSelectElement
   if(creditNum.value.length<6 || year.value=="year"){
    if(creditNum.value.length<6){
      this.inValidCard=true
    }else{
      this.inValidCard=false
    }
    if(year.value=="year"){
      this.inValidYear=true
    }else{
      this.inValidYear=false
    }
         console.log("errr");
         
     }
   else{
  let orders: Map<string, number> = new Map();
  let ord : OrderRequest =new OrderRequest()
  let credit: Credit =new Credit()
  for(let i=0 ;i<this.cart.length;i++){
      orders.set(this.cart[i].book.isbn,this.cart[i].amount)
  }

 
  credit.number=creditNum.value
  credit.expiryDate=year.value
  ord.orders=orders
  ord.creditCard=credit

  //request
  var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
  this.http.post<GenericResponse>('http://localhost:8080/api/v1/user/order',{} ,{headers}).subscribe((data)=>{
  console.log(data);

     
  });
  console.log(ord);
 
  

  localStorage.removeItem("cart");
  this.dialogRef.close()
  //window.location.reload()
}
}
}
