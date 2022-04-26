import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Subscription} from "../../core/model/Subscription";
import {User} from "../../core/model/User";




export interface IPagedResponse {
  total: number;
  data: User[];
}

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  constructor(private http : HttpClient) { }

  public event :[];
  public user :[];

  public  field : {[key : string]:any};










  //////////////////// Formation ////////////////////////////////////////




  // SerachMultiple(key:string) :Observable<Subscription[]>
  // {
  //   return this.http.get<Formation[]>('http://localhost:8090/Courses/SearchMultiple/'+key);
  // }


  // SerachRepi(key : string):Observable<any>
  // {
  //   return this.http.post<string>("http://localhost:8090/Courses/SearchHistorique/"+key,1)
  // }


  getSubscription():Observable<Subscription[]> {
    return this.http.get<Subscription[]>("http://localhost:8090/subscription/retrieveAllSubscriptions");
  }


  // getApprenantByFormation(i : number):Observable<User[]> {
  //   return this.http.get<User[]>("http://localhost:8090/Courses/ApprenantByFormation/"+i);
  // }



  addSubscription(f : Subscription): Observable<Subscription>
  {
    const headers = { 'content-type': 'application/json'}
    const body=JSON.stringify(f);
    console.log(body)
    return this.http.post<Subscription>("http://localhost:8090/subscription/addSubscription",f)
  }

  deleteSubscription(i:number): Observable<any> {

    return this.http.get<number>("http://localhost:8090/subscription/deleteUser"+i)
  }
  updateSubscription(f:Subscription,i:number): Observable<any>
  {
    const headers = { 'content-type': 'application/json'}
    const body=JSON.stringify(f);
    console.log(body)
    return this.http.put<Subscription>
    ("http://localhost:8090/subscription/updateSubscription/"+i,f);
  }

  // affectationApptoFormation(idApp :number , idFor : number,f :Formation): Observable<any>
  // {
  //   const headers = { 'content-type': 'application/json'};
  //   return this.http.post<Formation>("http://localhost:8090/Courses/affecterApprenantFormation/"+idApp+"/"+idFor+"/",f );
  //
  // }

  // getRevenueByFormation(i :number):Observable<number>
  // {
  //   return  this.http.get<number>('http://localhost:8090/Courses/getRevenueByFormation/'+i)
  // }
  //
  // getNbrApprenantByFormation():Observable<Object[]>
  // {
  //
  //   return this.http
  //     .get<Object[]>("http://localhost:8090/Courses/NbrApprenantByFormation")
  // }
  //
  // addLikes(i:number): Observable<any> {
  //
  //   return this.http.post<number>("http://localhost:8090/Courses/addLikes/"+i,1)
  // }
  //
  // addDisLikes(i:number): Observable<any> {
  //
  //   return this.http.post<number>("http://localhost:8090/Courses/addDisLikes/"+i,1)
  // }
  //
  //
  // uploadFile(file: FormData, i: number): Observable<any>
  // {
  //   return this.http.post<any>('http://localhost:8090/Courses/uploadMultipleFiles/'+i,file);
  // }
  //
  //
  // exportPDF():Observable<Blob>
  // {
  //   return this.http.get('http://localhost:8090/Courses/exportPDF',{responseType:'blob'} );
  // }
  //




}
