import {Component, Input, OnInit} from '@angular/core';

import {User} from "../../core/model/User";
import {UserService} from "../UserService/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {

  @Input() newUser:User = new User();

  message :string;
  constructor(private userService  : UserService,
              private router :Router) { }


  ngOnInit(): void {
  }
  AddUserComponent(){
    this.userService.AddUser(this.newUser).subscribe(user => {
      console.log(user);

    });


    this.router.navigate(['user']).then(() => {
      window.location.reload();
    });

  }

}
