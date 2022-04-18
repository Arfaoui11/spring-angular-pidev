import { Component, OnInit } from '@angular/core';
import {Complaint} from "../core/model/Complaint";
import {ComplaintService} from "./helpservice/complaint.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-complaint-list',
  templateUrl: './complaint-list.component.html',
  styleUrls: ['./complaint-list.component.css']
})
export class ComplaintListComponent implements OnInit {
  comlpaints: Complaint[];
  constructor(private complaintservices:ComplaintService , private router :Router) { }

  ngOnInit(): void {
    this.complaintservices.RetrieveComplaint().subscribe(data => {
      console.log(data);
      this.comlpaints = data;
    });
  }

}
