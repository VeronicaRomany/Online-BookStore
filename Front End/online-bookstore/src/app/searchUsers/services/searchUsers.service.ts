import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import {User} from "../../user";

@Injectable({
  providedIn: 'root'
})
export class SearchUsersService {
  currentUserInfo:User = new User
  constructor(private http:HttpClient,private token: TokenStorageService) { }
  userURL:string = "http://localhost:8080/users"

  getUsersInfo(userinfo: string):Observable<User[]>{
    console.log(userinfo)
    return this.http.get<User[]>(this.userURL+"/search")
  }
  makeManager(userName: string):Observable<User>{
    console.log(userName)
    return this.http.get<User>(this.userURL+"/searchUsers/promote")
  }
  setUser(user:User){
    this.currentUserInfo=user
  }
  getUser(){
    console.log(this.currentUserInfo)
    return this.currentUserInfo
  }
}
