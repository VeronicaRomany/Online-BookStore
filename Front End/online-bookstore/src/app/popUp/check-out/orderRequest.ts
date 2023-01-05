export class OrderRequest {
     creditCard:Credit=new Credit()
     orders: Map<string, number> = new Map();

}

export class Credit{
    number:string=""
    expiryDate:string=""
}