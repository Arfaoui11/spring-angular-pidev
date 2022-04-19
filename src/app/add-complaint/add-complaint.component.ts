import { Component, OnInit } from '@angular/core';
import {ComplaintService} from "../complaint-list/helpservice/complaint.service";
import {Router} from "@angular/router";
import {Complaint} from "../core/model/Complaint";

@Component({
  selector: 'app-add-complaint',
  templateUrl: './add-complaint.component.html',
  styleUrls: ['./add-complaint.component.css']
})
export class AddComplaintComponent implements OnInit {
  newComplaint = new Complaint();
  message :string;
  constructor(private complaintService  : ComplaintService,
              private router :Router) { }

  ngOnInit(): void {
  }
  AddComplaint(){
    this.complaintService.AddComplaint(this.newComplaint).subscribe(complaint => {
      console.log(complaint);

    });


    this.router.navigate(['complaint']).then(() => {
      window.location.reload();
    });

  }

}
