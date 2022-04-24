import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import {Observable, observable} from 'rxjs';
import {Offer} from "./core/model/Offres";

const httpOptions = {
  headers: new HttpHeaders( {'Content-Type': 'application/json'} )
};

// @ts-ignore
@Injectable({
  providedIn: 'root'
})
export class JobOfferSpaceService{


  getUrl = "http://localhost:8090/swagger-ui/index.html#/rest-controller-service/GetOfferUsingGET";
  addUrl = "http://localhost:8090/swagger-ui/index.html#/rest-controller-service/updateOfferUsingPOST";
  supUrl = "http://localhost:8090/swagger-ui/index.html#/rest-controller-service/deleteOfferUsingDELETE";
  updateUrl = "http://localhost:8090/swagger-ui/index.html#/rest-controller-service/updateOfferUsingPUT";
  getByDateCreation = "http://localhost:8090/swagger-ui/index.html#/rest-controller-service/OffresParDateCreationUsingGET";
  getByProfession= "http://localhost:8090/swagger-ui/index.html#/rest-controller-service/offerByProfessionUsingGET"
  getNbrOffer="http://localhost:8090/swagger-ui/index.html#/rest-controller-service/nbrOfferUsingGET"
  constructor(private _http:HttpClient) { }
  retrieveabonement(): Observable<Offer[]>{
    return this._http.get<Offer[]>(this.getUrl);
  }

  addOffres(offer: Offer):Observable<Offer>{
    return this._http.post<Offer>(this.addUrl, offer, httpOptions);
  }

  supprimerOffres(id : number) {

    return this._http.delete<Offer>(this.supUrl+"/"+id);
  }


  updateOffres(offer :Offer) : Observable<Offer>{
    return this._http.put<Offer>(this.updateUrl+"/"+Offer.idOffer, offer, httpOptions);
  }



  percentageByNumber(): Observable<any> {

    return this._http.get<number>(this.getNbrOffer);
  }


}
