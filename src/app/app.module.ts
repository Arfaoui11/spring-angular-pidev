import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {GoogleChartsModule} from "angular-google-charts";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
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
    ChangeBgDirective,
    HomeComponent,
    FooterComponent,
    NavbarComponent,
    LayoutComponent,
    SidbarComponent,
    WelcomeComponent,
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

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
