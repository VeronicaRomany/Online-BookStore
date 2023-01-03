import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-cart',
  templateUrl: './add-cart.component.html',
  styleUrls: ['./add-cart.component.css']
})
export class AddCartComponent implements OnInit {
  amount:number=1
  constructor(@Inject(MAT_DIALOG_DATA) public data:{bookId:number}) { }

  ngOnInit(): void {
    console.log(this.data.bookId);
    
  }
  increase(){
     this.amount++;
  }
  decrease(){
    if(this.amount>1){
      this.amount--;
    }

  }

}
