import { Component, Input, OnInit } from '@angular/core';
import { ReportingServiceService } from '../reporting-service/reporting-service.service';

@Component({
  selector: 'app-report-card',
  templateUrl: './report-card.component.html',
  styleUrls: ['./report-card.component.css']
})
export class ReportCardComponent implements OnInit {

  // pdfSrc = "D:\\CSED2024\\Fall 2022\\DBMS\\Online-BookStore\\Front End\\online-bookstore\\sample.pdf";
  // pdfSrc = 'https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf';

  pdf!:Uint8Array;
  @Input() endpoint:string = '';

  constructor(private service: ReportingServiceService) {
   }

  ngOnInit(): void {
    console.log(this.endpoint);
    this.service.getReport(this.endpoint).subscribe((data) =>{
      console.log(data);
      this.pdf = new Uint8Array(data);
      console.log(this.pdf);
    });
  }

  loadFile = (event:Event) =>{
    console.log("lol");
    if(event != null){
      console.log(event);
      let el = event.target as HTMLInputElement;
      if(el == null)  return;
      let file = el.files;
      let fileReader = new FileReader();
      fileReader.readAsArrayBuffer(file![0]);
      console.log(file![0]);
      fileReader.onloadend = (ev) =>{
        this.pdf = new Uint8Array(<Uint8Array>fileReader.result);
        console.log(this.pdf);
      };
    }

  }

}
