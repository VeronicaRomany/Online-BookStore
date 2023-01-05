import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { GenericResponse } from 'src/app/shared/GenericResponse';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import {User} from "../../user";

@Injectable({
  providedIn: 'root'
})
export class SearchUsersService {
  currentUserInfo:User = new User
  constructor(private http:HttpClient,private token: TokenStorageService) { }
  searchURL:string = "http://localhost:8080/api/v1/search/users"
  promoteURL:string = "http://localhost:8080/api/v1/manager/user-promotion"
  getUsersInfo(key: string):Observable<User[]>{
    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
    console.log(key)
    return this.http.post<User[]>(this.searchURL,{
      "key":key,
      "order":"A",
      "pageNumber":1,
      "countInPage":10000
    },{headers:headers})
  }
  makeManager(userUsername: string){
    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
    this.http.post<GenericResponse>(this.promoteURL,{"userUsername":userUsername},{headers:headers}).subscribe()
    console.log(userUsername)
  }

  setUser(user:User){
    this.currentUserInfo=user
  }
  getUser(){
    console.log(this.currentUserInfo)
    return this.currentUserInfo
  }
}
