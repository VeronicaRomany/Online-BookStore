export class Book{
    ISBN: number=0
    title:string=""
    authors:string[]=[]
    publisher:string=""
    publicationYear:number=0
    sellingPrice:number=0
    category:string=""
    imageLink:string=""
    minimumQuantity:number=0

    

    constructor(){}

     fillData(isbn:number,tit:string,auth:string[],pub:string,year:number,price:number,cat:string,link:string,min:number):void{
        this.ISBN=isbn
        this.title=tit
        this.authors=auth
        this.publisher=pub
        this.publicationYear=year
        this.sellingPrice=price
        this.category=cat
        this.imageLink=link
        this.minimumQuantity=min
    }

    
}