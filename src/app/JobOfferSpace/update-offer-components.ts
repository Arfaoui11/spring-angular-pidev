/*
import { Component, OnInit } from '@angular/core';
import {Offer, Offres} from "..//core/model/Offres";
import { JobOfferSpaceService} from "./job-offer-space.service";
import { ActivatedRoute,Router } from '@angular/router';


<!-- @Component({
  selector: 'app-update-Offres',
  templateUrl: './update-complaint.component.html',
  styleUrls: ['./update-complaint.component.css']-->
})-->
export class UpdateOfferComponents implements OnInit {
  currentOffres =new Offres();
  constructor(private activatedRoute: ActivatedRoute,
              private router :Router, private OffresService :OffresService) { }

  ngOnInit(): void {
    this.OffresService.consulterComplaint(this.activatedRoute.snapshot.params.id).
    subscribe( () =>{ this.currentOffres = Offer; });
  }
  updateComplaint() {
    this.OffresService.updateOffres(this.currentOffres, id).subscribe(() => {
        this.router.navigate(['/home/helpSpace-Management/ListOffres']);
      },() => { alert("Problème lors de la modification !"); }
    );
  }
}
-->
*/
