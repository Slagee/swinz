import { Component, OnInit } from '@angular/core';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import { RestApiService } from 'src/app/services/rest-api.service';
import { Room } from 'src/app/models/room.model';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent implements OnInit {

  faHome = faHome;

  months = [{id: 1, name: 'LEDEN'}, {id: 2, name:'ÚNOR'}, {id: 3, name:'BŘEZEN'}, {id: 4, name: 'DUBEN'}, {id: 5, name: 'KVĚTEN'},
            {id: 6, name: 'ČERVEN'}, {id: 7, name: 'ČERVENEC'}, {id: 8, name: 'SRPEN'}, {id: 9, name: 'ZÁŘÍ'}, {id: 10, name: 'ŘÍJEN'},
            {id: 11, name: 'LISTOPAD'}, {id: 12, name: 'PROSINEC'}];
  
  roomsData: Array<Room> = [];

  constructor(
    private readonly restApiService: RestApiService
  ) { }

  ngOnInit(): void {
    this.restApiService.getAllRoomData().subscribe(
      (rooms: Array<Room>) => {
        this.roomsData = rooms;
      },
      (error) => console.log(error),
      () => { 
        this.roomsData = this.roomsData.sort((a, b) => a.id - b.id);
        for (let i = 0; i < this.roomsData.length; i++) {
          this.roomsData[i].monthlyLight = [];
          this.roomsData[i].totalPowerConsumption = [];

          this.months.forEach(month => {
            this.restApiService.getMonthlyLight(this.roomsData[i].id, month.id).subscribe(
              (res) => {
                res = Math.round(res);
                this.roomsData[i].monthlyLight.push(res);
              }
            );
            this.restApiService.getMonthlyPower(this.roomsData[i].id, month.id).subscribe(
              (res) => {
                res = Math.round(res);
                this.roomsData[i].totalPowerConsumption.push(res);
              }
            )
          });
        }
      }
    );
  }
}
