import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts';
import { NgxSliderModule } from '@m0t0r/ngx-slider';

import { AppComponent } from './components/layout/app.component';
import { HomeComponent } from './components/home/home.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { RoomsComponent } from './components/rooms/rooms.component';
import { NotFoundComponent } from './error-pages/not-found/not-found.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { StatisticsComponent } from './components/statistics/statistics.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RoomsComponent,
    NotFoundComponent,
    StatisticsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ChartsModule,
    NgxSliderModule,
    FontAwesomeModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
