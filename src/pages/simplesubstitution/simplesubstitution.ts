import { Component } from '@angular/core';
import {Validators, FormBuilder, FormControl } from '@angular/forms';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-simplesubstitution',
  templateUrl: 'simplesubstitution.html'
})
export class SimpleSubstitutionPage {
  private inputText: string;
  private outputText: string;
  private key: string;
  private inputForm: any = {};
  private keyForm: any = {};
  private letterCountMap: any = {};
  private msgMap: any = {};


  private chartTypePos = 0;
  private chartTypes : string[] = ["BarChart", "PolarArea"];
  private alphabetLetters: string[] = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
  //Barchart
  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true,
  };
  public barChartLabels: string[] = this.alphabetLetters;
  public barChartType: string = 'bar';
  public barChartLegend: boolean = false;
  public barChartData: any[];

  public polarAreaChartLabels:string[] = this.alphabetLetters;
  public polarAreaChartData:number[];
  public polarAreaLegend:boolean = false;
  public polarAreaChartType:string = 'polarArea';


  constructor(public navCtrl: NavController, private formBuilder: FormBuilder) {
    this.initLetterCountMap();
    this.barChartData = [{ data: this.getLetterCounts() }];
    this.polarAreaChartData = this.getLetterCounts();
    this.inputText = '';
    this.key = '';
    this.outputText = '';
    //Forms
    this.inputForm = this.formBuilder.group({
      description: ['', Validators.required]
    });
    this.keyForm = this.formBuilder.group({
      key: ['', Validators.compose([Validators.pattern(/^(?=[a-z]{26}$)(?!.*(.).*\1).*$/m), Validators.required])]
    });
  }

  private calculateLetterFrequencies() {
    this.outputText = "";
    this.inputText = this.inputForm.value.description.toLowerCase();
    this.initLetterCountMap();
    for (var i = 0; i < this.inputText.length; i++) {
      if (this.letterCountMap[this.inputText[i]] != null) {
        this.letterCountMap[this.inputText[i]] += 1;
      }
    }

    var letterCounts: any = this.getLetterCounts();
    this.barChartData = letterCounts;
    this.polarAreaChartData = letterCounts;
  }



  private kryptographinator(encrypt: boolean){
    this.key = this.keyForm.value.key;
    this.outputText = "";
    var str = "";
    if(encrypt){
      for(var i = 0; i < 27; i++){
        this.msgMap[this.alphabetLetters[i]] = this.key[i];
      }
    }else{
      for(var i = 0; i < 27; i++){
        this.msgMap[this.key[i]] = this.alphabetLetters[i];
      }
    }
    for(var i = 0; i < this.inputText.length; i++){
      if(this.msgMap[this.inputText[i]] != null){
        str += this.msgMap[this.inputText[i]];
      } else {
        str += this.inputText[i];
      }
    }
    this.outputText = str;
  }

  private initLetterCountMap() {
    for(var i = 0; i < 27; i++){
      this.letterCountMap[this.alphabetLetters[i]] = 0;
    }
  }

  private getLetterCounts() {
    var array = [];
    for (let entry in this.letterCountMap) {
      array.push(this.letterCountMap[entry]);
    }
    return array;
  }

  private toggleChart(){
    this.chartTypePos++;
    if(this.chartTypePos > this.chartTypes.length - 1){
      this.chartTypePos = 0;
    }
  }

  private getChartType(){
    return this.chartTypes[this.chartTypePos];
  }

  private switch(){
    this.inputForm.value.description = this.outputText;
    this.calculateLetterFrequencies();
  }
}
