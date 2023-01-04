import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Book } from 'src/app/shared/Book';

@Component({
  selector: 'app-add-cart',
  templateUrl: './add-cart.component.html',
  styleUrls: ['./add-cart.component.css']
})
export class AddCartComponent implements OnInit {
  amount:number=1
  constructor(@Inject(MAT_DIALOG_DATA) public data:{book:Book},private dialogRef: MatDialogRef<AddCartComponent>) { }

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
  addToCart(){
        var cart:Object[] =[]
        if(localStorage.getItem("cart")!=null)
        {
        cart= JSON.parse(localStorage.getItem("cart")!)
        
        }
        var j= {
        book :this.data.book,
        amount: this.amount

        }
        cart.push(j)
        localStorage.setItem("cart",JSON.stringify(cart))
        this.dialogRef.close()
  }

}
