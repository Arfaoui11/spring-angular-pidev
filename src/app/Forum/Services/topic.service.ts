import { Injectable } from '@angular/core';
import {User} from "../../core/model/User";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Topic} from "../../core/model/Topic";
import {Appointment} from "../../core/model/Appointment";

const httpOptions = {
  headers: new HttpHeaders( {'Content-Type': 'application/json'} )
};


export interface IPagedResponse {
  total: number;
  data: User[];
}

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  apiURL = "http://localhost:8099/Topic/getAllTopics";
 // getSingleSub = "http://localhost:8090/heplpspace/retrieve-Appointment-by-ID";
  addUrl = "http://localhost:8099/Topic/addTopic";
  supUrl = "http://localhost:8099/Topic/deleteTopic";
  updateUrl = "http://localhost:8099/Topic/updateTopic";
  //private getUrlexcel = 'http://localhost:8090/heplpspace/download/appointments.xlsx';

  constructor(private http:HttpClient) { }

  retrievetopic(): Observable<Topic[]>{
    return this.http.get<Topic[]>(this.apiURL);
  }


  deleteTopic(i:number): Observable<any> {

    return this.http.get<number>("http://localhost:8099/Topic/deleteTopic/"+i)
  }

  AddTopic(t: Topic):Observable<Topic>{
    return this.http.post<Topic>(this.addUrl, t, httpOptions);
  }

  updateTopic(t :Topic) : Observable<Topic>{
    return this.http.put<Topic>(this.updateUrl+"/"+t.idTopic, t, httpOptions);
  }
}
