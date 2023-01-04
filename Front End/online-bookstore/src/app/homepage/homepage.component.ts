import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddCartComponent } from '../popUp/add-cart/add-cart.component';
import { Book } from '../shared/Book';
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
 books:Book[]=[]
  constructor(public dialog:MatDialog) { }


  
  ngOnInit(): void {
    this.books.push(this.dummy()[0])
    this.books.push(this.dummy()[1])
    this.books.push(this.dummy()[0])
    this.books.push(this.dummy()[1])
  }
  
  dummy(){
    var book =new Book()
    var book1 =new Book()
    var books:Book[]=[]
    book.ISBN=1
    book.authors= ["mark","vero"]
    book.category="Art"
    book.imageLink= "https://diwanegypt.com/wp-content/uploads/2022/12/9781925946789-450x600.jpg"
    book.minimumQuantity=5
    book.publicationYear=2009
    book.publisher="M.E"
    book.sellingPrice=90000
    book.title="Database"
    book1.ISBN=2
    book1.authors= ["peter","bero"]
    book1.category="science"
    book1.imageLink= "https://diwanegypt.com/wp-content/uploads/2022/12/9781974708895-450x600.jpg"
    book1.minimumQuantity=4
    book1.publicationYear=2001
    book1.publisher="M.eeeE"
    book1.sellingPrice=80000
    book1.title="Football"
    books.push(book,book1)
    return books
  }
  addToCart(book:Book){
    this.dialog.open(AddCartComponent,{data:{book:book }});
  }

}
