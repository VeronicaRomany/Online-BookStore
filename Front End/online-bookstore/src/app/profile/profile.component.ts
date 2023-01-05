import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile/services/profile.service';
import { Router } from '@angular/router';
import {MatDialog} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../_services/token-storage.service";
import {User} from "../user";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  currentUserInfo: User = new User();
  notLogIn = true
  userID: number = 0;
  loggedIn: boolean = false
  manager: boolean = false

  constructor(private token: TokenStorageService, private profile: ProfileService, private router: Router, public dialog: MatDialog, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.userID = this.token.getUser().userId;
    if (this.currentUser.username != undefined) {
      this.notLogIn = false
      console.log("ii")
    }
    this.profile.getUserInfo().subscribe(result => {
      this.currentUserInfo = result
      this.profile.setUser(this.currentUserInfo)
      console.log(result)
    })
    if(this.currentUser.isMgr){
      this.manager=true
    }
  }
  isMyProfile(){
    return  this.userID == this.token.getUser().userId;
  }
  editProfile(){
    this.router.navigate(['/', 'Edit'])
  }
}
