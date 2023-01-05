import { Component, OnInit } from '@angular/core';
import { SearchUsersService } from './services/searchUsers.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../_services/token-storage.service";
import {User} from "../user";

@Component({
  selector: 'app-SearchUsers',
  templateUrl: './searchUsers.component.html',
  styleUrls: ['./searchUsers.component.css']
})
export class SearchUsersComponent implements OnInit {
  currentUser: any;
  currentUserInfo: User = new User();
  users:User[] = [];
  notLogIn = true
  userID: number = 0;
  loggedIn: boolean = false
  promote: boolean = false
  constructor(private token: TokenStorageService,
              //private dialogRef: MatDialogRef<SearchUsersComponent>,
              private route:ActivatedRoute, private searchUsers: SearchUsersService, private router: Router, public dialog: MatDialog, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.searchUsers.getUsersInfo("").subscribe(result => {
      this.users = result
      console.log(result)
    })
  }
  onSearch(){
    var textSearch = document.getElementById('search') as HTMLInputElement
    this.searchUsers.getUsersInfo(textSearch.value).subscribe(result => {
      this.users = result
      console.log(result)
    })
  }
  noUsers(){
    return this.users.length==0
  }
  isManager(){
    return this.token.getUser().type == "manager";
  }

  onPromote(userName:string){
    this.searchUsers.makeManager(userName).subscribe(result => {
      console.log(result)
    })
   
   
  }
  photo(type:string){
    return type=="manager"
  }
}
