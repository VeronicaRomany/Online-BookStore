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
        ISBN: ['', Validators.required,Validators.minLength(13),Validators.maxLength(13)],
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
      console.log(params.data)
      if(params.data!=null){
            console.log("edit Book with isbn = ",params.data);  
            this.edit=true
            this.modifiedBook.setOldISBN(params.data)
                      // dh msh s7 kda
            this.http.get<BookInfoResponse>("http://localhost:8080/manager/book",params.data).subscribe((data:any) =>{
                  this.editBook=data

                    console.log(this.editBook)
                    //Dummy Data
                    // this.editBook.ISBN="1234" 
                    // this.editBook.authors=["samy","salma"]
                    // this.editBook.category="art"
                    // this.editBook.imageURL="data:image/jpeg;base64,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"
                    // this.editBook.stock=50
                    // this.editBook.threshold=70
                    // this.editBook.pubYear=2022
                    // this.editBook.publisher="Veroo"
                    // this.editBook.price=300
                    // this.editBook.title="alfn asloob 7iah"
                    

                    this.handleOnEdit()
                  
                    this.f['ISBN'].setValue(this.editBook.ISBN)
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
      this.f['ISBN'].setValue(this.editBook.ISBN)
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

      this.modifiedBook.fillData(this.f['ISBN'].value,this.f['title'].value,this.array,this.f['Publisher'].value,
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