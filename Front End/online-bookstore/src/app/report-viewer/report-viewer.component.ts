import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-report-viewer',
  templateUrl: './report-viewer.component.html',
  styleUrls: ['./report-viewer.component.css']
})
export class ReportViewerComponent implements OnInit {

  salesReport:string = "/sale";

  topCustomers:string = "/topCustomers";
  bestSelling:string = "/bestSellings";

  width = 700;
  height = 750;

  constructor() { }

  ngOnInit(): void {
  }

  getStyle(width:number, height:number){
    return `style: width ${width}; height: ${height}`; 
  }

  getSmallStyle(width:number, height:number){
    return `style: width ${width}; height: ${height / 2 - 10}`; 
  }

}
