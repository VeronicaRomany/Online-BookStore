import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';


import { GenericResponse } from 'src/app/shared/GenericResponse';
import {  ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/user';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { ProfileService } from '../profile/services/profile.service';
import { modifyUserRequest } from '../modifyUserRequest';


export default class Validation {
  static match(controlName: string, checkControlName: string): ValidatorFn {

    return (controls: AbstractControl) => {

      const checkControl = controls.get(checkControlName);

      if (checkControl?.errors && !checkControl.errors['matching']) {
        return null;
      }
      if (controlName !== checkControl?.value) {
        console.log("Not equal")
        controls.get(checkControlName)?.setErrors({ matching: true });
        return { matching: true };
      } else {
        return null;
      }
    };
  }
}
@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  form: FormGroup = new FormGroup({
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    username: new FormControl(''),
    email: new FormControl(''),
    old_password: new FormControl(''),
    new_password: new FormControl(''),
    phoneNumber: new FormControl(''),
    address : new FormControl(''),
  });
  submitted = false;
  urllink:string="";
  file:File={
    lastModified: 0,
    name: '',
    webkitRelativePath: '',
    size: 0,
    type: '',
    arrayBuffer: function (): Promise<ArrayBuffer> {
      throw new Error('Function not implemented.');
    },
    slice: function (start?: number | undefined, end?: number | undefined, contentType?: string | undefined): Blob {
      throw new Error('Function not implemented.');
    },
    stream: function (): ReadableStream<Uint8Array> {
      throw new Error('Function not implemented.');
    },
    text: function (): Promise<string> {
      throw new Error('Function not implemented.');
    }
  }

  newAccount:modifyUserRequest= new modifyUserRequest()
  user:User=new User()
  currentUserInfo=this.profile.getUser()
  //currentUserInfo: User=new User()






  constructor(private formBuilder: FormBuilder, private http:HttpClient,
    private profile: ProfileService, private tokenStorage: TokenStorageService,
    private router:Router,private modify: modifyUserRequest) {}

  ngOnInit(): void {





    this.form = this.formBuilder.group(
      {
        firstname: ['', Validators.required],
        lastname: ['', Validators.required],
        username: [
          '',
          [
            Validators.required
          ]
        ],
        email: ['', [ Validators.email]],
        old_password: [
          '',
          [
            Validators.required
          ]
        ],
        new_password: ['',
        [Validators.minLength(6),
        Validators.maxLength(40)]
      ],
        phoneNumber: ['',[Validators.required,Validators.minLength(9),Validators.maxLength(11)]],

        address : ['',''],

      },

      {
        validators: [Validation.match(this.tokenStorage.getUser().password, 'old_password')]
      }
    );
    
    console.log(this.currentUserInfo);
    this.f['firstname'].setValue(this.currentUserInfo.firstName);
    this.f['lastname'].setValue(this.currentUserInfo.lastName);
    this.f['username'].setValue(this.currentUserInfo.username);
    this.f['phoneNumber'].setValue(this.currentUserInfo.phone);
    this.f['address'].setValue(this.currentUserInfo.address);
    this.f['email'].setValue(this.currentUserInfo.email);
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {

    this.submitted = true;

    if (this.form.invalid) {
      console.log(JSON.stringify(this.form.value, null, 2))
      return;
    }

    var jsonString = JSON.stringify(this.form.value, null, 2)
    console.log(jsonString);

    this.newAccount.newFName = this.f['firstname'].value
    this.newAccount.newLName = this.f['lastname'].value
    console.log(this.f['new_password'].value,"new_password")
    if(this.f['new_password'].value!=''){
      
       this.newAccount.newPassword=this.f['new_password'].value
       console.log(this.newAccount.newPassword)
      }
    else
       this.newAccount.newPassword=this.f['old_password'].value
    this.newAccount.oldUserName = this.f['username'].value
    this.newAccount.newUsername = this.f['username'].value
    this.newAccount.newPhone = this.f['phoneNumber'].value
    this.newAccount.newEmail = this.f['email'].value
    this.newAccount.newAddress = this.f['address'].value



    var NewAccountJsonString = JSON.stringify(this.newAccount)
    console.log(NewAccountJsonString)

    this.urllink=""

    var headers=new HttpHeaders().append("Authorization","Bearer "+this.tokenStorage.getUser().token)
    this.http.patch<GenericResponse>("http://localhost:8080/api/v1/user",JSON.parse(NewAccountJsonString),{headers:headers}).subscribe((data) =>{
        if(data.state){
            // request login
            console.log(data)
            this.router.navigate(['/', 'Profile'])
        }else{
          window.alert(data.message+",Try again")
        }
    })
}

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }


}
