import { Component, OnInit } from '@angular/core';
import {Complaint} from "../core/model/Complaint";
import {ComplaintService} from "../helpservice/complaint.service";
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
  deleteComplaintById(c: Complaint)
  {
    console.log("supp supprimé"+c);
    let conf = confirm("Etes-vous sûr ?");
    if (conf)
      this.complaintservices.DeleteComplaint(c.idCom).subscribe(() => {
        console.log("appointment supprimé");
        this.SuprimerComplaintDuTableau(c);
      });
  }

  SuprimerComplaintDuTableau(c :Complaint) {
    this.comlpaints.forEach((cur, index) => {
      if(c.idCom=== cur.idCom) {
        this.comlpaints.splice(index, 1);
      }
    });
  }


}
