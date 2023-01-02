import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';


export default class Validation {
  static match(controlName: string, checkControlName: string): ValidatorFn {
    return (controls: AbstractControl) => {
      const control = controls.get(controlName);
      const checkControl = controls.get(checkControlName);

      if (checkControl?.errors && !checkControl.errors['matching']) {
        return null;
      }

      if (control?.value !== checkControl?.value) {
        controls.get(checkControlName)?.setErrors({ matching: true });
        return { matching: true };
      } else {
        return null;
      }
    };
  }
}


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})


export class RegisterComponent implements OnInit {

  form: FormGroup = new FormGroup({
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
    phoneNumber: new FormControl(''),
    address : new FormControl(''),
    type: new FormControl('')
   
  });
  submitted = false;
  phoneLen : boolean = false;
 

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        firstname: ['', Validators.required],
        lastname: ['', Validators.required],
        username: ['', [Validators.required,Validators.pattern("^[A-Za-z][A-Za-z0-9]*$")] ],
        email: ['', [ Validators.required,Validators.email,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
        password: [ '',[ Validators.required, Validators.minLength(6), Validators.maxLength(40)  ]],
        confirmPassword: ['', Validators.required],
        phoneNumber: ['',[Validators.required,Validators.minLength(9),Validators.maxLength(11)]],
        address : ['',Validators.required],
        type : ['',Validators.required]
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
  }


  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onReset():void{
    this.submitted = false;
    this.phoneLen=false;
    this.form.reset();
  }

  onSubmit():void{
  

    this.submitted = true;
    if (this.form.invalid) {
      return;
    }

    let phone   = this.f['phoneNumber'].value
 
    if(phone.length == 10){
      this.phoneLen=true;
      return;
    }

    console.log(this.form.value)
   


  }

}
