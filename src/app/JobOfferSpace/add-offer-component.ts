import { Component, OnInit } from '@angular/core';
import {Offer} from "..//core/model/Offres";
import { JobOfferSpaceService} from "./job-offer-space.service";
import { Router } from '@angular/router';


@Component({
selector: 'app-addoffer',
templateUrl: './addoffer.component.html',
styleUrls: ['./addoffer.component.css']
})
export class AddofferComponent implements OnInit {

newOffer = new Offer();
message :string;
constructor(private _service : JobOfferSpaceService,
private router :Router) { }

ngOnInit(): void {
}

addOffer(){
this._service.addOffres(this.newOffer).subscribe(offer => {
console.log(offer);

});

this.router.navigate(['offer']).then(() => {
window.location.reload();
});

}
  }
