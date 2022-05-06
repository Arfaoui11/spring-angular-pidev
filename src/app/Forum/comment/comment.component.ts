import { Component, OnInit } from '@angular/core';
import {Topic} from "../../core/model/Topic";
import {TopicService} from "../Services/topic.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

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
  }

