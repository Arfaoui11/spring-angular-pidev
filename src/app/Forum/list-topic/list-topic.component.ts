import { Component, OnInit } from '@angular/core';


import {MatSnackBar} from "@angular/material/snack-bar";
import {User} from "../../core/model/User";
import {Topic} from "../../core/model/Topic";
import {ActivatedRoute, Router} from "@angular/router";
import {TopicService} from "../Services/topic.service";
import {Formation} from "../../core/model/Formation";
import {Appointment} from "../../core/model/Appointment";


@Component({
  selector: 'app-list-topic',
  templateUrl: './list-topic.component.html',
  styleUrls: ['./list-topic.component.css']
})
export class ListTopicComponent implements OnInit {

  ///////Affichage////////////

  topics: Topic[];
  newTopic = new Topic();
  message :string;

  constructor(private topicService: TopicService, private router: Router,private snackbar:MatSnackBar, private activateRoute: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.topicService.retrievetopic().subscribe(data => {
      console.log(data);
      this.topics = data;
    });
  }
///////////////Suppression//////////////////////////
  deleteTopic(i :number)
  {
    console.log(i);
    this.topicService.deleteTopic(i)
      .subscribe(response => {
        this.topics = this.topics.filter(item => item.idTopic !== i);
      });
    this.snackbar.open(' delete successfully', 'Undo', {
      duration: 2000
    });
  }
  ////////////////////////////

  AddTopic(){
    this.topicService.AddTopic(this.newTopic).subscribe(t => {
      console.log(t);

    });

    this.router.navigate(['topic']).then(() => {
      window.location.reload();
    });

  }

  ////////////update////////////////////

  updateTopic() {
    this.topicService.updateTopic(this.newTopic).subscribe(() => {
        this.router.navigate(['topic']);
      },(error) => { alert("Error of update !"); }
    );
  }

////////////////////////////
}
