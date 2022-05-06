import { Component, OnInit } from '@angular/core';

import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CommentService} from "../Services/comment.service";

@Component({
  selector: 'app-list-comment',
  templateUrl: './list-comment.component.html',
  styleUrls: ['./list-comment.component.css']
})
export class ListCommentComponent implements OnInit {

  comments: Comment[];
  newComment = new Comment();
  message :string;

  constructor(private commentService:CommentService, private router: Router,private snackbar:MatSnackBar, private activateRoute: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.commentService.retrievecomment().subscribe(data => {
      console.log(data);
      this.comments = data;
    });
  }


  AddComment(){
    this.commentService.AddComment(this.newComment).subscribe(t => {
      console.log(t);

    });

    this.router.navigate(['comment']).then(() => {
      window.location.reload();
    });

  }

}
