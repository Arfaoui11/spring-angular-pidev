import { Injectable } from '@angular/core';

import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../../core/model/User";
import {Observable} from "rxjs";



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

export class CommentService {


  apiURL = "http://localhost:8099/Comment/getAllCommentsByTopic";

  addUrl = "http://localhost:8099/Comment/addComment";



  constructor(private http: HttpClient) {
  }

  retrievecomment(): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.apiURL);
  }

  AddComment(t: Comment): Observable<Comment> {
    return this.http.post<Comment>(this.addUrl, t, httpOptions);
  }
}
