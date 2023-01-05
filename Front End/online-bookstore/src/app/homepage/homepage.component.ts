import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddCartComponent } from '../popUp/add-cart/add-cart.component';
import { Book } from '../shared/Book';
import { TokenStorageService } from '../_services/token-storage.service';
import { searchRequest } from './booksearch';
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
 books:Book[]=[]
 currentPage:number=0
 numOfPosts:number=0
 postFlag:boolean=false
 maxPagesNum:number=0
  constructor(public dialog:MatDialog,private http:HttpClient, private token: TokenStorageService) { }


  
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
    book.ISBN="1"
    book.authors= ["mark","vero"]
    book.category="Art"
    book.imageURL= "https://diwanegypt.com/wp-content/uploads/2022/12/9781925946789-450x600.jpg"
    book.stock=5
    book.pubYear=2009
    book.publisher="M.E"
    book.price=90
    book.title="Database"
    book.threshold=2
    book1.threshold=4
    book1.ISBN="2"
    book1.authors= ["peter","bero"]
    book1.category="science"
    book1.imageURL= "https://diwanegypt.com/wp-content/uploads/2022/12/9781974708895-450x600.jpg"
    book1.stock=4
    book1.pubYear=2001
    book1.publisher="M.eeeE"
    book1.price=80
    book1.title="Football"
    books.push(book,book1)
    return books
  }
  addToCart(book:Book){
    this.dialog.open(AddCartComponent,{data:{book:book }});
  }
   search(){

    var title = document.getElementById("title") as HTMLInputElement
    var publisher = document.getElementById("publisher") as HTMLInputElement
    var author = document.getElementById("author") as HTMLInputElement
    var isbn = document.getElementById("isbn") as HTMLInputElement
    var category = document.getElementById("category") as HTMLSelectElement
    console.log(title.value);
    console.log(category.value);
      var req = new searchRequest()
      req.ISBN=isbn.value
      req.authorName=author.value
      req.category=category.value == "Category"? "":category.value
      req.pageNumber=0
      req.publisher=publisher.value
      req.title=title.value
      console.log(req);
      
//       var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
//  this.http.post<Book[]>("http://localhost:8080/api/v1/search/books-by-details",req,{headers}).subscribe((data:any) =>{
//     console.log(data);
    
//   })
      
    
   }
   nextPage(){
      this.currentPage++
   }
   previousPage(){
    if(this.currentPage>0){
      this.currentPage--
    }

   }
   order(){

   }
   isManager(){
    return this.token.getUser().isMgr
  }
}
