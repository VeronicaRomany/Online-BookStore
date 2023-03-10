import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../_services/auth.service.service';
import { TokenStorageService } from '../_services/token-storage.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],

})

export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  username='';

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      console.log("Has token")
      this.isLoggedIn = true;
      this.username = this.tokenStorage.getUser().username;
    }
    if(this.isLoggedIn){
      this.router.navigate(['/', 'Home'])
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

     this.authService.login(username, password).subscribe((dataReturned)=> {
         
          console.log(dataReturned)
         let data=dataReturned.mgr
         let token=dataReturned.token
           this.tokenStorage.saveToken(username);
           this.tokenStorage.saveUser({"username":username,"password":password,"isMgr":data,"token":token});
           console.log(this.tokenStorage.getUser().mgr)

    
           this.isLoginFailed = false;
           this.isLoggedIn = true;
           this.username = this.tokenStorage.getUser().username;
           window.location.reload();
           console.log(this.tokenStorage.getUser().mgr)

           this.router.navigate(['/', 'Home'])
       },(error) => {
        console.log(error.status);
        this.errorMessage='Please enter valid username or password'
        this.isLoginFailed = true;
       }
   );
   this.reloadPage
  }

  reloadPage(): void {
    window.location.reload();
  }
}
