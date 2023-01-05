import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { GenericResponse } from '../shared/GenericResponse';
import { TokenStorageService } from '../_services/token-storage.service';
import { ManagerOrder } from './managerOrders';
import { OrdersService } from './services/orders.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
   orders:ManagerOrder[]=[]
  constructor(private http:HttpClient, private serv: OrdersService,private token:TokenStorageService) { }

  ngOnInit(): void {
    //this.orders=this.dummy()
   this.serv.getManagerOrders().subscribe(res=>{
     console.log(res);
       this.orders=res
    })
  }
  confirm(orderID:number){
    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
       this.http.post<GenericResponse>("http://localhost:8080/api/v1/manager/order-confirmation",{orderID},{headers}).subscribe(res=>{

      console.log(res);
      
       })
  }

  dummy(){
    var book =new ManagerOrder()
    var book1 =new ManagerOrder()
    var books:ManagerOrder[]=[]
    book.ISBN="1"
   
    book.category="Art"
    book.imageURL= "https://diwanegypt.com/wp-content/uploads/2022/12/9781925946789-450x600.jpg"
    book.stock=5
    book.pubYear=2009
    book.publisher="M.E"
    book.quantity=5
    book.title="Database"
    book.threshold=2
    book1.threshold=4
    book1.ISBN="2"
   
    book1.category="science"
    book1.imageURL= "https://diwanegypt.com/wp-content/uploads/2022/12/9781974708895-450x600.jpg"
    book1.stock=4
    book1.pubYear=2001
    book1.publisher="M.eeeE"
    book1.quantity=8
    book1.title="Football"
    books.push(book,book1)
    return books
  }
}
