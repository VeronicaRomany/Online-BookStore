import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReportingServiceService {

  baseUrl:string = "";

  constructor(private http:HttpClient) {

   }

   getReport(path:string){
    let options = {
      'responseType'  : 'arraybuffer' as 'json',
      'Access-Control-Allow-Origin': '*'
    }
    return this.http.get<ArrayBuffer>(this.baseUrl + path, options);
   }
}
