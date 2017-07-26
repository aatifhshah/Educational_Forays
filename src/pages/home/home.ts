import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { SimpleSubstitutionPage } from '../simplesubstitution/simplesubstitution'
import { Rc4Page } from '../rc4/rc4';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  private checkType: any = Cryptions;
  constructor(public navCtrl: NavController) {

  }

  public goToPage(buttonClicked:Cryptions){
    switch(buttonClicked){
      case Cryptions.SimpleSubstitution:
        this.navCtrl.push(SimpleSubstitutionPage);
        break;
      case Cryptions.RC4:
        this.navCtrl.push(Rc4Page);
        break;
      default:
        console.log("fijk");
        break;
    }
  }
}

export enum Cryptions { SimpleSubstitution, RC4}
