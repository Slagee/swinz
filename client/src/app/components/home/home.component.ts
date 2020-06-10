import { Component, OnInit } from '@angular/core';
import { faCheckCircle, faTimesCircle, faTimes, faCircle } from '@fortawesome/free-solid-svg-icons';
import { Label, MultiDataSet } from 'ng2-charts';
import { ChartType } from 'chart.js';
import { Options } from '@m0t0r/ngx-slider';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  doughnutChartLabels: Label[] = ['Zapnuto', 'Vypnuto', 'Zbyva'];
  doughnutChartData: MultiDataSet = [
    [60, 10, 20]
  ];
  doughnutChartType: ChartType = 'doughnut';

  faCheckCircle = faCheckCircle;
  faTimesCircle = faTimesCircle;
  faTimes = faTimes;
  faCircle = faCircle;

  sliderValue: number = 22.0;
  sliderOptions: Options = {
    floor: 15,
    ceil: 30,
    step: 0.1
  }

  constructor() { }

  ngOnInit(): void {
  }

}
