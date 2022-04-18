import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Complaint} from "../../core/model/Complaint";

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {
 private apiURL = "http://localhost:8090/heplpspace/retrieve-All-Complaints";

  constructor(private http:HttpClient) { }
  RetrieveComplaint(): Observable<Complaint[]>{
    return this.http.get<Complaint[]>(this.apiURL);
  }
}
