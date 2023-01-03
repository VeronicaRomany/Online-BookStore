import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators, AbstractControl, FormArray } from '@angular/forms';
import Validation from 'src/app/registeration/register/register.component';

@Component({
  selector: 'app-new-book',
  templateUrl: './new-book.component.html',
  styleUrls: ['./new-book.component.css']
})
export class NewBookComponent implements OnInit {


 form: FormGroup = this.formBuilder.group({
    ISBN: new FormControl(''),
    title: new FormControl(''),
    Publisher: new FormControl(''),
    price: new FormControl(''),
    category: new FormControl(''),
    publicationYear:new FormControl(''),
    minimumQuantity:new FormControl(''),
    Authors: this.formBuilder.array([])
   
    
   
  });
  submitted = false;
  authorError=false;
  values :string[]=[];

  
 

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        ISBN: ['', Validators.required],
        title: ['', Validators.required],
        Publisher: ['', Validators.required],
        price: ['',Validators.required],
        category : ['',Validators.required],
        publicationYear: ['',Validators.required],
        minimumQuantity: ['',Validators.required],
        Authors:['']
      }
    );
  }


  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }



  addvalue(){

    
    this.values.push("")

    }

  onReset():void{
    this.submitted = false;
    this.authorError=false;
    this.form.reset();
  }


  authorsHandle():void{
    let array:string[]=[]
    let index=0;
    
     
    for(let i=0 ; i<this.values.length;i++){
      let x= "name_" +i
      let temp=(document.getElementById(x) as unknown as HTMLInputElement).value
      if(temp!="" && temp!=null){
          array[index++]=temp
      }
    }

    console.log(array)
    
    this.f['Authors'].setValue(array)

    console.log(this.f['Authors'])

    if(array.length<1){
      console.log("mktbsh author ")
      this.authorError=true
    }else{
      this.authorError=false
    }
  }

  onSubmit():void{
    this.authorsHandle()
   
  
    this.submitted = true;
    if (this.form.invalid) {
      return;
    }


    if(this.authorError){
      return;
    }

    console.log(this.form.value)

  }

}
