import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Question} from "../core/model/Question";
import {Result} from "../core/model/Result";
import {Formation} from "../core/model/Formation";
import {Quiz} from "../core/model/Quiz";

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http : HttpClient) { }

  getQuestionJson(){
    return this.http.get<any>("assets/questions.json");
  }

  getQuizQuestion(id:number): Observable<any[]>
  {
    return this.http.get<any[]>("http://localhost:8090/SpringMVC/form/getQuizQuestion/"+id);
  }

  saveScore(re : Result,idU:number,idQ:number):Observable<any>
  {
    const headers = { 'content-type': 'application/json'}
    const body=JSON.stringify(re);
    console.log(body)
    return this.http.post<Result>("http://localhost:8090/SpringMVC/form/SaveScore/"+idU+"/"+idQ,re)
  }


  addQuiz(quiz : Quiz,idF:number):Observable<Quiz>
  {
    return this.http.post<Quiz>("http://localhost:8090/SpringMVC/form/addQuiz/"+idF,quiz);
  }

  addQuestion(qu : Question,idQuiz:number):Observable<Question>
  {
    return this.http.post<Question>("http://localhost:8090/SpringMVC/form/addQuestionAndAsigntoQuiz/"+idQuiz,qu);
  }

  getQuizByForm(id:number):Observable<Quiz[]>
  {
    return this.http.get<Quiz[]>('http://localhost:8090/SpringMVC/form/getQuizByFormation/'+id);
  }


  deleteQuiz(i:number): Observable<any> {

    return this.http.get<number>("http://localhost:8090/SpringMVC/form/DeleteQuiz/"+i)
  }




}
