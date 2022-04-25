import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {GoogleChartsModule} from "angular-google-charts";

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NgxPaginationModule} from "ngx-pagination";
import {MatButtonModule} from "@angular/material/button";
import {MatSnackBarModule} from "@angular/material/snack-bar";

import {ListFomateurComponent} from "./CoursesSpace/list-fomateur/list-fomateur.component";
import {FormationComponent} from "./CoursesSpace/formation/formation.component";
import {AddFomateurComponent} from "./CoursesSpace/add-fomateur/add-fomateur.component";
import { ListeFormationComponent } from './CoursesSpace/liste-formation/liste-formation.component';
import {DayPilotModule} from "daypilot-pro-angular";



import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import {TreeViewModule} from "@syncfusion/ej2-angular-navigations";
import { QuizComponent } from './CoursesSpace/quiz/quiz.component';
import { ChangeBgDirective } from './change-bg.directive';
import {NgxQRCodeModule} from "ngx-qrcode2";
import { HomeComponent } from './Back-End/home/home.component';
import { FooterComponent } from './Back-End/footer/footer.component';
import { NavbarComponent } from './Back-End/navbar/navbar.component';
import { LayoutComponent } from './Back-End/layout/layout.component';
import { SidbarComponent } from './Back-End/sidbar/sidbar.component';
import {WelcomeComponent} from "./CoursesSpace/welcome/welcome.component";
import {ListOfPartnersComponent} from "./ExchangeStudents/list-of-partners/list-of-partners.component";
import {
  AddPartnerInstitutionComponent
} from "./ExchangeStudents/add-partner-institution/add-partner-institution.component";
import {RouterModule, Routes} from "@angular/router";
import {PartnerInstitutionService} from "./ExchangeStudents/services/partner-institution.service";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';
import {StatUniversityComponent} from "./ExchangeStudents/stat-university/stat-university.component";
import {ChartsModule} from "ng2-charts";

import {LoginComponent} from "./Back-End/login/login.component";
import { ComplaintListComponent } from './complaint-list/complaint-list.component';
import { AddComplaintComponent } from './add-complaint/add-complaint.component';
import { UpdateComplaintComponent } from './update-complaint/update-complaint.component';
import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import { AddAppointmentComponent } from './add-appointment/add-appointment.component';
import { UpdateAppointmentComponent } from './update-appointment/update-appointment.component';
import {HomeFComponent} from "./FontEnd/home-f/home-f.component";
import {FooterFComponent} from "./FontEnd/footer-f/footer-f.component";
import {LayoutFComponent} from "./FontEnd/layout-f/layout-f.component";
import {NavbarFComponent} from "./FontEnd/navbar-f/navbar-f.component";
import {BlogFormationComponent} from "./CoursesSpace/blog-formation/blog-formation.component";




FullCalendarModule.registerPlugins([
  dayGridPlugin,
  interactionPlugin
]);



@NgModule({
  declarations: [
    AppComponent,
    ListFomateurComponent,
    AddFomateurComponent,
    FormationComponent,
    ListeFormationComponent,
    QuizComponent,
    HomeFComponent,
    ChangeBgDirective,
    HomeComponent,
    LoginComponent,
    FooterComponent,
    NavbarComponent,
    LayoutComponent,
    SidbarComponent,
    WelcomeComponent,
    ListOfPartnersComponent,
    AddPartnerInstitutionComponent,
    StatUniversityComponent,
    ComplaintListComponent,
    AddComplaintComponent,
    UpdateComplaintComponent,
    AppointmentListComponent,
    AddAppointmentComponent,
    UpdateAppointmentComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgxPaginationModule,
    NgxQRCodeModule,
    GoogleChartsModule,
    MatButtonModule,
    MatSnackBarModule,
    BrowserAnimationsModule,
    TreeViewModule,
    NgbModule,
    DayPilotModule,
    ToastrModule.forRoot(),
    ChartsModule



  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
