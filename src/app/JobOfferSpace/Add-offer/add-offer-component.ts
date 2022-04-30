/*import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../core/model/User";
import {NgxQrcodeElementTypes, NgxQrcodeErrorCorrectionLevels} from "ngx-qrcode2";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Offer} from "../../core/model/Offres";
import {JobOfferSpaceService} from "../job-offer-space.service";

@Component({
  selector: 'app-Add-Offer',
  templateUrl: './Add-Offer-compnent.html',
  styleUrls: ['./Add-Offer-component.css']
})
export class AddOfferComponentComponent implements OnInit {

  listOffer : Offer[];
  @Input() offer:Offer=new Offer();
  idOffer : number;

  elementType= NgxQrcodeElementTypes.URL;
  correctionLevel = NgxQrcodeErrorCorrectionLevels.MEDIUM;
  formateur : User;
  imgURL: any;
  public imagePath :FileList;
  show : boolean = false;

  constructor(private services : JobOfferSpaceService,private snackbar:MatSnackBar) {

    this.getOffer()
  }

  ngOnInit(): void {
    this.formateur = new User();
  }


  getOffer(){
    this.services.getOffer().subscribe(
      (data:Offer[])=>{this.listOffer = data});

    return this.listOffer;
  }

  dataId(i:number)
  {
    console.log(i);
    this.idOffer = i;
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

  UpdateOffer(f: Offer,id : number)
  {

    this.services.updateOffres(f,id).subscribe(
      data=>{
        this.getOffer();
      });

  }

  ToggleForm()
  {
    this.show = ! this.show;
  }

  addOffer(i:number)
  {

    this.services.addOffres(this.offer,i).subscribe(
      data=>{
        this.getOffer();
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

  deleteOffer(i :number)
  {
    this.services.deleteOffer(i).subscribe(() => {
        this.listOffer = this.listOffer.filter(item => item.idOffer !== i);
      });
  }

  ExportPDF() {
    this.services.exportPDF().subscribe(
      x=>
      {
        const blob = new Blob([x],{type : 'application/pdf'})

        if(window.navigator && window.navigator.msSaveOrOpenBlob)
        {
          window.navigator.msSaveOrOpenBlob(blob);
          return;
        }

        const data = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = data;
        link.download = 'Offer.pdf';
        link.dispatchEvent( new MouseEvent('click',{bubbles:true,cancelable:true,view:window}))

        setTimeout(function () {
          window.URL.revokeObjectURL(data);
          link.remove();
        },1000)

      }
    )

  }
}
*/
