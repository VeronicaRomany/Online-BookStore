export class ModifyBookRequest{
     oldISBN:string="";
     newISBN:string="";
     newTitle:string="";
     newPublisher:string="";
     newPubYear:number=0;
     newPrice:number=0;
     newCategory:string="";
     newStock:number=0;
     newThreshold:number=0;
     newImageURL:string="";
     newAuthors:string[]=[];


     fillData(oisbn:string,isbn:string,tit:string,auth:string[],pub:string,year:number,price:number,cat:string,link:string,min:number,thre:number):void{
        this.oldISBN=oisbn
        this.newISBN=isbn
        this.newTitle=tit
        this.newAuthors=auth
        this.newPublisher=pub
        this.newPubYear=year
        this.newPrice=price
        this.newCategory=cat
        this.newImageURL=link
        this.newStock=min
        this.newThreshold=thre
    }


    setOldISBN(isbn:string){
        this.oldISBN=isbn
    }
}