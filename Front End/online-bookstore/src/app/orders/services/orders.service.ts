import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { ManagerOrder } from '../managerOrders';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http:HttpClient,private token:TokenStorageService) { }

  getManagerOrders():Observable<ManagerOrder[]>{
   
    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
    return this.http.get<ManagerOrder[]>("http://localhost:8080/api/v1/manager/orders/test",{headers})
  }
}
