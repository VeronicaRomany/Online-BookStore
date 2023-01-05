import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-headers',
  templateUrl: './headers.component.html',
  styleUrls: ['./headers.component.css']
})
export class HeadersComponent implements OnInit {
 isLoggedIn=false
 username?: string;
  constructor(private token:TokenStorageService,private router:Router ) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.token.getToken();
    if (this.isLoggedIn) {
      const user = this.token.getUser();
      this.username = user.username;
    }
  }
  logout(){
    this.token.signOut()
    this.router.navigate(['/', 'Home'])
    window.location.reload();

  }
  isManager(){
    return this.token.getUser().isMgr
  }
}
