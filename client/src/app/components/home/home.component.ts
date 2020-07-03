import { Component, OnInit, OnDestroy } from '@angular/core';
import { faCheckCircle, faTimesCircle, faTimes, faCircle } from '@fortawesome/free-solid-svg-icons';
import { Label, MultiDataSet } from 'ng2-charts';
import { ChartType } from 'chart.js';
import { Options } from '@m0t0r/ngx-slider';
import { Room } from 'src/app/models/room.model';
import { RestApiService } from 'src/app/services/rest-api.service';
import { interval } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {

  // Chart section
  doughnutChartLabels: Label[] = ['Zapnuto', 'Vypnuto', 'Zbývá'];
  doughnutChartData: MultiDataSet = [
    []
  ];
  doughnutChartType: ChartType = 'doughnut';

  // Slider section
  sliderValue: number = 21.0;
  sliderOptions: Options = {
    floor: 15,
    ceil: 30,
    step: 0.1
  }

  // Icons section
  faCheckCircle = faCheckCircle;
  faTimesCircle = faTimesCircle;
  faTimes = faTimes;
  faCircle = faCircle;

  // Variables
  private timer: any
  roomsData: Array<Room> = [];
  globalRadiatorState: boolean = false;

  constructor(
    private readonly restApiService: RestApiService
  ) { }

  ngOnDestroy(): void {
    this.timer.unsubscribe();
  }

  ngOnInit(): void {
    this.restApiService.getAllRoomData()
    .subscribe(
      (roomsData: Array<Room>) => 
        {
          this.roomsData = roomsData;
          this.sliderValue = this.roomsData[0].selectedTemperature;
          this.sortRooms();
        },
      () => {  }
    );
    
    this.chartSetting();
    this.timer = interval(30000).subscribe(_x => this.getRooms());
  }

  getRooms(): void {
    this.restApiService.getAllRoomData()
    .subscribe(
      (roomsData: Array<Room>) => {
        this.roomsData = roomsData;
        this.sortRooms();
      },
      () => { }
    );
  }

  changeGlobalRadiatorSettings(): void {
    this.roomsData.forEach(room => {
      room.radiatorForcedDown = !room.radiatorForcedDown;
      this.restApiService.updateRoomById(room, room.id).subscribe(
      () => { this.getRooms(); }
      )
    });
  }

  sliderChange(): void {
    this.roomsData.forEach(room => {
      room.selectedTemperature = this.sliderValue;
      this.restApiService.updateRoomById(room, room.id).subscribe(
        () => { this.getRooms() }
      )
    });
  }

  sortRooms() {
    this.roomsData = this.roomsData.sort((a, b) => a.id - b.id);
  }

  chartSetting() {
    const oneDay = 1000*60*60*24;
    const firstDay = new Date(new Date().getFullYear(), 0, 1).setHours(0,0,0);
    const today = new Date().setHours(0,0,0);
    const lastDay = new Date(new Date().getFullYear(), 11, 0).setHours(0, 0, 0);

    const diffDays = Math.round(Math.abs((firstDay - today) / oneDay));
    const remainingDays = Math.round(Math.abs((today - lastDay) / oneDay));
    

    this.restApiService.getTotalDaysOfRadiatorOn().subscribe(
      (res: number) => {
        this.doughnutChartData[0][0] = res;
        this.doughnutChartData[0][1] = diffDays - res;
        this.doughnutChartData[0][2] = remainingDays;
      }
    );
  }
}
