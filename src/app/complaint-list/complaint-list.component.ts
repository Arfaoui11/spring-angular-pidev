import { Component, OnInit } from '@angular/core';
import {Complaint} from "../core/model/Complaint";

@Component({
  selector: 'app-complaint-list',
  templateUrl: './complaint-list.component.html',
  styleUrls: ['./complaint-list.component.css']
})
export class ComplaintListComponent implements OnInit {
  comlpaints: Complaint[];
  constructor() { }

  ngOnInit(): void {
  }

}
