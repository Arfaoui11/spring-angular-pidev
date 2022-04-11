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


const routes: Routes =

   [ { path: '', redirectTo: 'home', pathMatch: 'full' },
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

      }


  ]
  }
];





@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
