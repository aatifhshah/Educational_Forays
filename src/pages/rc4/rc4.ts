import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

/*
  Generated class for the Rc4 page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-rc4',
  templateUrl: 'rc4.html'
})
export class Rc4Page {
  arr:any = new Array(16);
  key:any = ["1a", "2b", "3c", "4d", "5e", "6f", "77"];
  S:any = new Array(256);
  K:any = new Array(256);
  i:any = 0;
  j:any= 0;
  quantity:number = 0;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    for(var i = 0; i < 16; i++){
      this.arr[i] = new Array(16);
    }
    this.initSimple();
  }

  initSimple(){
    var row;
    var col;
    for(var i = 0; i < 256; i++){
      row = Math.floor(i/16);
      col = i % 16;
      this.arr[row][col] = i.toString(16);
    }
  }

  initArray(){
    for(var i = 0; i < 256; i++){
      this.S[i] = i;
      this.K[i] = this.key[i % this.key.length];
    }

    var j = 0;
    var i = 0;
    for( i; i < 256; i++){
      j = (j + this.S[i] + parseInt(this.K[i])) % 256;
      this.swap(this.S, i, j);
    }
    i = j = 0;
    this.i = i;
    this.j = j;
    this.displayArray();
  }

  swap(array, a, b){
    var temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

  displayArray() {
    var row;
    var col;
    for(var i = 0; i < 256; i++){
      row = Math.floor(i/16);
      col = i % 16;
      this.arr[row][col] = this.S[i].toString(16);
    }
  }

  generateKeystreamByte(){
    this.initArray();
    var t;
    var keystreamByte;
    var I = 0;
    var J = 0;
    for(var i = 0; i < this.quantity; i++){
      I = (I + 1) % 256;
      J = (J + this.S[I]) % 256;
      this.swap(this.S, I, J);
      t = (this.S[I] + this.S[J]) % 256;
      keystreamByte = this.S[t];
    }
    this.i = I;
    this.j = J;
    this.displayArray();
  }
}
