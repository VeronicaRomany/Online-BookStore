import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators, AbstractControl, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import Validation from 'src/app/registeration/register/register.component';
import { NewBookService } from '../new-book.service';
import{Book} from 'src/app/shared/Book'
import { GenericResponse } from 'src/app/shared/GenericResponse';
import { ModifyBookRequest } from 'src/app/shared/ModifyBookRequest';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { BookInfoResponse } from 'src/app/shared/BookInfoResponse';

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
    threshold:new FormControl(""),
    bookCover:new FormControl(""),
    Authors: this.formBuilder.array([])
   
    
   
  });
  edit:boolean=false
  submitted:boolean = false;
  authorError:boolean=false;
  file:any;
  imageLink:string=""
  values :string[]=[];
  array:string[]=[]
  newBook:Book=new Book()
  editBook:Book=new Book()
  modifiedBook:ModifyBookRequest=new ModifyBookRequest()
  
 

  constructor(private formBuilder: FormBuilder,private route:ActivatedRoute, private http:HttpClient,
    private readonly newbookservice:NewBookService, private router:Router,private token:TokenStorageService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        ISBN: ['', [Validators.required,Validators.minLength(13),Validators.maxLength(13)]],
        title: ['', Validators.required],
        Publisher: ['', Validators.required],
        price: ['',Validators.required],
        category : ['',Validators.required],
        publicationYear: ['',Validators.required],
        minimumQuantity: ['',Validators.required],
        threshold: ['',Validators.required],
        Authors:[''],
        bookCover:[""]
      }
    );




     this.route.queryParams.subscribe((params:any) =>{
      console.log( params.data)
      
      if(params.data!=null){
            console.log("edit Book with isbn = ",params.data);  
            this.edit=true
            this.modifiedBook.setOldISBN(params.data)

            var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
                      // dh msh s7 kda
            this.http.post<BookInfoResponse>("http://localhost:8080/api/v1/manager/book/get",params.data,{headers}).subscribe((data:any) =>{
                    this.editBook=data

                    console.log(this.editBook)

                    this.handleOnEdit()
                  
                    this.f['ISBN'].setValue(this.editBook.isbn)
                    this.f['title'].setValue(this.editBook.title)
                    this.f['Publisher'].setValue(this.editBook.publisher)
                    this.f['publicationYear'].setValue(this.editBook.pubYear)
                    this.f['price'].setValue(this.editBook.price)
                    this.f['category'].setValue(this.editBook.category)
                    this.f['minimumQuantity'].setValue(this.editBook.stock)
                    this.f['bookCover'].setValue(this.editBook.imageURL)
                    this.f['threshold'].setValue(this.editBook.threshold)
                    this.imageLink=this.editBook.imageURL

             })
      
          }})
  }


  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }


  onEdit():void{
    this.onSubmit()
  }

  addvalue(){
    this.values.push("")
    }

  handleOnEdit():void{
    let i=0
    while(i<this.editBook.authors.length){
      this.addvalue()
      i++
    }
  }

  onReset():void{
    this.submitted = false;
    this.authorError=false;
    this.imageLink=""
    this.values=[]
    this.form.reset();

    if(this.edit){
      this.f['ISBN'].setValue(this.editBook.isbn)
      this.editBook.authors=[]
    }
  }


  authorsHandle():void{
          
          let index=0;
          for(let i=0 ; i<this.values.length;i++){
            let x= "name_" +i
            let temp=(document.getElementById(x) as unknown as HTMLInputElement).value
            if(temp!="" && temp!=null){
                this.array[index++]=temp
            }
          }
          this.f['Authors'].setValue(this.array)

          if(this.array.length<1){
            this.authorError=true
          }else{
            this.authorError=false
          }
  }


  SelectFile(event:any){
    console.log(this.submitted)
    this.file = event.target.files[0];
    this.newbookservice.upload(this.file).subscribe((url: string) => {
      this.f['bookCover'].setValue(url) ,
      this.imageLink=url})
  }




  onSubmit():void{
    this.submitted = true;
    this.authorsHandle()
    
    if (this.form.invalid) {
      return;
    }
    if(this.authorError){
      return;
    }

    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)

    
    console.log("FORM VALUES >>>",this.form.value)

    if(!this.edit){

      this.newBook.fillData(this.f['ISBN'].value,this.f['title'].value,this.array,this.f['Publisher'].value,
                          this.f['publicationYear'].value,this.f['price'].value,this.f['category'].value,
                          this.f['bookCover'].value,this.f['minimumQuantity'].value,this.f['threshold'].value)
      
      console.log(this.newBook)
      let jsonString = JSON.stringify(this.newBook)
      console.log(JSON.parse(jsonString))

      // auth ttzwd f al request
      
      this.http.post<GenericResponse>("http://localhost:8080/api/v1/manager/book",JSON.parse(jsonString),{headers:headers}).subscribe((data) =>{
          console.log(data)
          if(data.state){
            window.alert(data.message)
            this.router.navigate(['/', 'Home'])
          }else{
            window.alert(data.message+", Try again")

          }
        })

     }else{

      this.modifiedBook.fillData(this.f['ISBN'].value,this.f['ISBN'].value,this.f['title'].value,this.array,this.f['Publisher'].value,
                                  this.f['publicationYear'].value,this.f['price'].value,this.f['category'].value,
                                  this.f['bookCover'].value,this.f['minimumQuantity'].value,this.f['threshold'].value)

      console.log(this.modifiedBook)
      let jsonString = JSON.stringify(this.modifiedBook)
      console.log(JSON.parse(jsonString))


        // 3ayzen al authentication
      this.http.patch<GenericResponse>("http://localhost:8080/api/v1/manager/book",JSON.parse(jsonString),{headers:headers}).subscribe((data)=>{
        if(data.state){
          window.alert(data.message)
          this.router.navigate(['/', 'Home'])
        }else{
          window.alert(data.message+", Try again")
        }
      })

      

    }
  }
}