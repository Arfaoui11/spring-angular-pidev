/*import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../core/model/User";
import {NgxQrcodeElementTypes, NgxQrcodeErrorCorrectionLevels} from "ngx-qrcode2";
import {MatSnackBar} from "@angular/material/snack-bar";
import {JobOfferSpaceService} from "../job-offer-space/job-offer-space.service";
import {Candidacy} from "../../core/model/Candidacy";

@Component({
  selector: 'app-Add-Candidacy',
  templateUrl: './Add-Candidacy-component.html',
  styleUrls: ['./Add-Candidacy-component.css']
})
export class AddCandidacyComponent implements OnInit {

  listCandidacy : Candidacy[];
  @Input() candidacy:Candidacy=new Candidacy();
  idCandidacy : number;

  elementType= NgxQrcodeElementTypes.URL;
  correctionLevel = NgxQrcodeErrorCorrectionLevels.MEDIUM;
  Candidacy : User;
  imgURL: any;
  public imagePath :FileList;
  show : boolean = false;

  constructor(private services : JobOfferSpaceService,private snackbar:MatSnackBar) {

    this.getCandidacy()
  }

  ngOnInit(): void {
    this.Candidacy = new User();
  }


  getCandidacy(){
    this.services.getOffer().subscribe(
      (data:Candidacy[])=>{this.listCandidacy = data});

    return this.listCandidacy;
  }

  dataId(i:number)
  {
    console.log(i);
    this.idCandidacy = i;
  }

  onFileSelected(event : any) {

    const file : FileList = event?.target?.files;


    var reader = new FileReader();

    this.imagePath = file;

    reader.readAsDataURL(file[0]);
    reader.onload = (_event) => {
      this.imgURL = reader.result;
    }
  }

  UpdateCandidacy(c: Candidacy,id : number)
  {



      this.services.updateCandidacy(c,id).subscribe(
        data=>{
          this.getCandidacy();
        });

  }

  ToggleForm()
  {
    this.show = ! this.show;
  }

  addCandidacy(i:number)
  {

    this.services.addCandidacy(this.candidacy,i).subscribe(
      data=>{
        this.getCandidacy();
      });

    const formData = new FormData();

    for (let i = 0 ;i<this.imagePath.length ; i++)
    {
      const element  =  this.imagePath[i];

      formData.append('files',element);
    }

    this.snackbar.open(' ajout avec succees', '', {
      duration: 2000
    });

    this.services.uploadFile(formData,1).subscribe(res => {
      console.log(res)
    });
  }

  deleteCandidacy(i :number)
  {
    this.services.deleteOffer(i).subscribe(() => {
        this.listCandidacy = this.listCandidacy.filter(item => item.idCandidacy !== i);
      });
  }


}







*/
