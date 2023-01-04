import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import {User} from "../../user";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  currentUserInfo:User = new User
  constructor(private http:HttpClient,private token: TokenStorageService) { }
  userURL:string = "http://localhost:8080/users"

  getUserInfo(userID: number | undefined):Observable<User>{
    console.log(userID)
    return this.http.get<User>(this.userURL+"/profile/"+userID)
  }
  makeManager(userID: number):Observable<User>{
    console.log(userID)
    return this.http.get<User>(this.userURL+"/profile/promote"+userID)
  }
  setUser(user:User){
    this.currentUserInfo=user
  }
  getUser(){
    console.log(this.currentUserInfo)
    return this.currentUserInfo
  }
}
