import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Book } from 'src/app/shared/Book';
import { GenericResponse } from 'src/app/shared/GenericResponse';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.css']
})
export class PlaceOrderComponent implements OnInit {

  amount:number=1
  constructor(@Inject(MAT_DIALOG_DATA) public data:{book:Book},private dialogRef: MatDialogRef<PlaceOrderComponent>,private http:HttpClient,private token:TokenStorageService) { }

  ngOnInit(): void {
    console.log(this.data.book);
    
  }
  increase(){
     this.amount++;
  }
  decrease(){
    if(this.amount>1){
      this.amount--;
    }
    
  }
  addToOrders(){
    var ord ={
      isbn: this.data.book.isbn,
      quantity:this.amount
    }
    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
    this.http.post<GenericResponse>("http://localhost:8080/api/v1/manager/order",ord,{headers}).subscribe((data:any) =>{
      console.log(data);
      
      
    })
       
        this.dialogRef.close()
  }
}
