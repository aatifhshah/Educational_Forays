import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { Rc4Page } from '../pages/rc4/rc4';
import { SimpleSubstitutionPage } from '../pages/simplesubstitution/simplesubstitution'
import '../../node_modules/chart.js/dist/Chart.bundle.min.js';
import {ChartsModule} from 'ng2-charts/charts/charts';


@NgModule({
  declarations: [
    MyApp,
    HomePage,
    SimpleSubstitutionPage,
    Rc4Page,
  ],
  imports: [
    ChartsModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    SimpleSubstitutionPage,
    Rc4Page,
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}]
})
export class AppModule {}
