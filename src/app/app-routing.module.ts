import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListFomateurComponent} from "./CoursesSpace/list-fomateur/list-fomateur.component";
import {FormationComponent} from "./CoursesSpace/formation/formation.component";
import {ListeFormationComponent} from "./CoursesSpace/liste-formation/liste-formation.component";
import {QuizComponent} from "./CoursesSpace/quiz/quiz.component";
import {HomeComponent} from "./Back-End/home/home.component";
import {AddFomateurComponent} from "./CoursesSpace/add-fomateur/add-fomateur.component";
import {WelcomeComponent} from "./CoursesSpace/welcome/welcome.component";

import {
  AddPartnerInstitutionComponent
} from "./ExchangeStudents/add-partner-institution/add-partner-institution.component";
import {ListOfPartnersComponent} from "./ExchangeStudents/list-of-partners/list-of-partners.component";
import {StatUniversityComponent} from "./ExchangeStudents/stat-university/stat-university.component";
import {LoginComponent} from "./Back-End/login/login.component";
import {AppointmentListComponent} from "./appointment-list/appointment-list.component";
import {AddAppointmentComponent} from "./add-appointment/add-appointment.component";
import {ComplaintListComponent} from "./complaint-list/complaint-list.component";
import {AddComplaintComponent} from "./add-complaint/add-complaint.component";
import {HomeFComponent} from "./FontEnd/home-f/home-f.component";
import {LayoutFComponent} from "./FontEnd/layout-f/layout-f.component";
import {BlogFormationComponent} from "./CoursesSpace/blog-formation/blog-formation.component";


const routes: Routes =

   [

     {path:'ll',component: HomeComponent },
     {path:'login',component: LoginComponent },
     { path: '',  redirectTo: '/front/frontEnd/homeF', pathMatch: 'full' },

     {
       path: 'front',
       component: LayoutFComponent,
       children: [
         {
           path: 'frontEnd',
           children: [

             { path: 'blogF', component: BlogFormationComponent },
             { path: 'homeF', component: HomeFComponent },


           ]
         },
       ]
     },
  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: 'Formation-management',
        children: [

          { path: 'addFormateur', component: AddFomateurComponent },
          { path: 'formateur', component: ListFomateurComponent },
          { path: 'addFormation', component: FormationComponent },
          { path: 'listFormateur', component: ListFomateurComponent } ,
          { path: 'listFormation', component: ListeFormationComponent },
          { path: 'quiz', component: QuizComponent },
          { path: 'quizWelcome', component: WelcomeComponent },

        ]
      },
      {
        path:'Exchange-Student-Management',
        children: [
          { path: 'addUniversity', component: AddPartnerInstitutionComponent },
          { path: 'ListUniversities', component: ListOfPartnersComponent },
          { path :  'statUniversities', component: StatUniversityComponent}

        ]

      },
      {
        path: 'helpSpace-Management',
        children: [

          { path: 'addAppointment', component: AddAppointmentComponent },
          { path: 'LisAppointment', component: AppointmentListComponent },
          { path: 'addComplaint', component: AddComplaintComponent },
          { path: 'ListComplaint', component: ComplaintListComponent } ,

        ]

      }


  ]
  }
];





@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
