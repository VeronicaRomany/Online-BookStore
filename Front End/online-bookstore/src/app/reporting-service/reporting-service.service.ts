import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';

const BASE_URL:string = 'http://localhost:8080/api/v1/manager/reports';
// const BASE_URL = "";
@Injectable({
  providedIn: 'root'
})
export class ReportingServiceService {


  constructor(private http:HttpClient, private tokenStorage:TokenStorageService) {

   }

   getReport(path:string){
    console.log(this.tokenStorage.getToken());
    let headers:HttpHeaders = new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Authorization': 'Bearer ' + this.tokenStorage.getUser().token
    });
    return this.http.get<ArrayBuffer>(BASE_URL + path, {headers, 'responseType'  : 'arraybuffer' as 'json'});
   }
}
