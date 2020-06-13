import { Component, OnInit } from '@angular/core';
import { faCheckCircle, faTimesCircle, faTimes, faCircle } from '@fortawesome/free-solid-svg-icons';
import { Label, MultiDataSet } from 'ng2-charts';
import { ChartType } from 'chart.js';
import { Options } from '@m0t0r/ngx-slider';
import { Room } from 'src/app/models/room.model';
import { RestApiService } from 'src/app/services/rest-api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  roomsData: Array<Room> = [];

  doughnutChartLabels: Label[] = ['Zapnuto', 'Vypnuto', 'Zbývá'];
  doughnutChartData: MultiDataSet = [
    [60, 10, 20]
  ];
  doughnutChartType: ChartType = 'doughnut';

  faCheckCircle = faCheckCircle;
  faTimesCircle = faTimesCircle;
  faTimes = faTimes;
  faCircle = faCircle;

  sliderValue: number = 23.0;
  sliderOptions: Options = {
    floor: 15,
    ceil: 30,
    step: 0.1
  }

  constructor(
    private readonly restApiService: RestApiService
  ) { }

  ngOnInit(): void {
    this.restApiService.getAllRoomData()
      .subscribe(
        (roomsData: Array<Room>) => this.roomsData = roomsData,
        (error) => console.log(error),
        () => {}
      );
  }

}
