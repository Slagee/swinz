import { Component, OnInit } from '@angular/core';
import { faCheckCircle, faTimesCircle, faTimes, faCircle } from '@fortawesome/free-solid-svg-icons';
import { Label, MultiDataSet } from 'ng2-charts';
import { ChartType } from 'chart.js';
import { Options } from '@m0t0r/ngx-slider';
import { Room } from 'src/app/models/room.model';
import { RestApiService } from 'src/app/services/rest-api.service';
import { error } from 'protractor';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  roomsData: Array<Room> = [];

  globalRadiatorState: boolean = false;

  doughnutChartLabels: Label[] = ['Zapnuto', 'Vypnuto', 'Zbývá'];
  doughnutChartData: MultiDataSet = [
    [60, 10, 30]
  ];
  doughnutChartType: ChartType = 'doughnut';

  faCheckCircle = faCheckCircle;
  faTimesCircle = faTimesCircle;
  faTimes = faTimes;
  faCircle = faCircle;

  sliderValue: number = 21.0;
  sliderOptions: Options = {
    floor: 15,
    ceil: 30,
    step: 0.1
  }

  constructor(
    private readonly restApiService: RestApiService
  ) { }

  ngOnInit(): void {
    this.getRooms();
  }

  getRooms(): void {
    this.restApiService.getAllRoomData()
    .subscribe(
      (roomsData: Array<Room>) => this.roomsData = roomsData,
      (error) => console.log(error),
      () => {}
    );
  }

  changeGlobalRadiatorSettings(): void {
    this.getRooms();
    this.roomsData.forEach(room => {
      room.radiatorState = this.globalRadiatorState;
      this.restApiService.updateRoomById(room, room.id)
        .subscribe(
          success => console.log(room.radiatorState),
          error => alert(error)
        );
      });
    this.globalRadiatorState == true ? this.globalRadiatorState = false : this.globalRadiatorState = true;
  }
}
