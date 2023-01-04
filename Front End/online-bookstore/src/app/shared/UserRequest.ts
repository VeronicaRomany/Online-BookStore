export class UserRequest{   
     username:string=""
     password:string="";
     firstName:string=""
     lastName:string=""
     email:string=""
     phone:string=""
     address:string=""

     fillData(user:string,pass:string,first:string,last:string,mail:string,pho:string,add:string){
        this.username=user
        this.password=pass
        this.firstName=first
        this.lastName=last
        this.email=mail
        this.phone=pho
        this.address=add
     }

}