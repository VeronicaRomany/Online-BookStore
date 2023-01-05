export class Book{
   isbn: string=""
    title:string=""
    authors:string[]=[]
    publisher:string=""
    pubYear:number=0
    price:number=0
    category:string=""
    imageURL:string=""
    stock:number=0
    threshold:number=0
    

    constructor(){}

     fillData(isbn:string,tit:string,auth:string[],pub:string,year:number,price:number,cat:string,link:string,min:number,thre:number):void{
        this.isbn=isbn
        this.title=tit
        this.authors=auth
        this.publisher=pub
        this.pubYear=year
        this.price=price
        this.category=cat
        this.imageURL=link
        this.stock=min
        this.threshold=thre
    }

    
}