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

  roomsData: Array<Room>;


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
      }
    );

    this.months.forEach(month => {
      
    });
  }

  getLightStatistics(roomId: number, monthId: number) {
    return this.restApiService.getMonthlyLight(roomId, monthId).subscribe();
  }

}
