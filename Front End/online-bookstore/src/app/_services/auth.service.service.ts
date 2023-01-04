import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { DataReturned } from '../dataReturned';

const AUTH_API:string = 'http://localhost:8080/api/v1/user/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    let incoded=btoa(username+':'+password)
    console.log(incoded)

    var headers=new HttpHeaders().append("Authorization","Basic "+incoded)
    console.log(headers)
    return this.http.post<DataReturned>(AUTH_API,{} ,{headers});
  }
}
