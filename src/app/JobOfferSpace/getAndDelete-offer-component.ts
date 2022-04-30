/*import { Component, OnInit } from '@angular/core';
import { Offer } from "..//core/model/Offres";

import { Router } from '@angular/router';
import { UpdateofferComponent } from '../backoffice/updateoffer/updateoffer.component';

@Component({
selector: 'app-offer',
templateUrl: './offer.component.html',
styleUrls: ['./offer.component.css']
})
export class OffersComponent implements OnInit {
offers: Offers[];

constructor(private _service:OffersService , private router :Router) { }

ngOnInit(): void {
this._service.retrieveoffer().subscribe(offer => {
console.log(offer);
this.offers = offer;
});

}

supprimerOffer(offer: Offer)
{
console.log("suppppppppppppppppppppppppppppp supprimé"+a);
let conf = confirm("Etes-vous sûr ?");
if (conf)
this._service.supprimerOffer(a.idSubscription).subscribe(() => {
console.log("offer deleted");
this.SuprimerOfferDuTableau(a);
});
}

SuprimerOfferDuTableau(offer : Offer) {
this.offers.forEach((cur, index) => {
if(offer.idSubscription=== cur.idSubscription) {
this.offer.splice(index, 1);
}
  });
}


  }
*/
