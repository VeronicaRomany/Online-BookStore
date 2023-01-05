import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ManagerOrder } from '../managerOrders';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http:HttpClient) { }

  getManagerOrders():Observable<ManagerOrder[]>{
   

    return this.http.get<ManagerOrder[]>("http://localhost:8080/api/v1/manager/orders")
  }
}
