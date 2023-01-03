import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddCartComponent } from '../popUp/add-cart/add-cart.component';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
 books:number[]=[1,2,3,4,5,6,7,8]
  constructor(public dialog:MatDialog) { }


  
  ngOnInit(): void {
  }
  
  addToCart(){
    this.dialog.open(AddCartComponent,{data:{bookId:6 }});
  }

}
