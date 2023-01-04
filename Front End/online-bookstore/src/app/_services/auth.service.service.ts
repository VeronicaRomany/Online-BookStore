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
    console.log(btoa(username+':'+password))

    const httpOptions = {
      headers: new HttpHeaders().append('Authorization','Basic cHk6MTIzNDU2')
    };
    console.log(httpOptions)
    return this.http.post<DataReturned>(AUTH_API ,httpOptions);
  }
}
