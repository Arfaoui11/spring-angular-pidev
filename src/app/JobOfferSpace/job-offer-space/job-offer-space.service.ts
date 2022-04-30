import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import {Observable, observable} from 'rxjs';
import {Offer} from "../../core/model/Offres";
import {Candidacy} from "../../core/model/Candidacy";

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


  updateOffres(offer: Offer, id: number) : Observable<Offer>{
    return this._http.put<Offer>(this.updateUrl+"/"+offer.idOffer, offer, httpOptions);
  }



  percentageByNumber(): Observable<any> {

    return this._http.get<number>(this.getNbrOffer);
  }


  getOffer() {

  }

  deleteOffer(i: number) {

  }

  uploadFile(file: FormData, i: number): Observable<any>
  {
    return this._http.post<any>('http://localhost:8090/Courses/uploadMultipleFiles/'+i,file);
  }


  exportPDF():Observable<Blob>
  {
    return this._http.get('http://localhost:8090/Courses/exportPDF',{responseType:'blob'} );
  }


  addCandidacy(candidacy: Candidacy, i: number) :Observable<Candidacy>{
    return this._http.post<Candidacy>(this.addUrl, candidacy, httpOptions)
  }

  updateCandidacy(c: Candidacy, id: number) : Observable<Candidacy> {
    return this._http.put<Candidacy>(this.updateUrl,Candidacy, httpOptions);
  }

}
