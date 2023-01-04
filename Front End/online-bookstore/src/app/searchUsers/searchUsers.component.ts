import { Component, OnInit } from '@angular/core';
import { SearchUsersService } from '../SearchUsers/services/SearchUsers.service';
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
    // this.route.queryParams.subscribe((params:any) =>{
    //   console.log(params.data+" <<<<<<<<<<<<<<<<<<<<<<<<<<")
    //   if(params.data!=null){
    //     this.userID=params.data
    //   }else{
    //     this.currentUser = this.token.getUser();
    //     this.userID = this.token.getUser().userId;
    //     if (this.currentUser.username != undefined) {
    //       this.notLogIn = false
    //
    //       console.log("ii")
    //     }
    //     else{
    //       this.router.navigate(['/', 'Home'])
    //     }
    //   }
    // } )
  }
  onSearch(){
    var textSearch = document.getElementById('search') as HTMLInputElement
    this.searchUsers.getUsersInfo(textSearch).subscribe(result => {
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
  getProfile(id:number){
    this.router.navigate([ '/','Profile'],{queryParams:{data:id}})
    //this.dialogRef.close()
  }
  onPromote(id:number){
    this.searchUsers.makeManager(id).subscribe(result => {
      console.log(result)
    })
    this.getProfile(id)
  }
  noSellerPic(photo:string){
    return (photo==null||photo=='')
  }
}
