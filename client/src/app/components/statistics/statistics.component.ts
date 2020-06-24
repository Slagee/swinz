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

  months = ['LEDEN', 'ÚNOR', 'BŘEZEN', 'DUBEN', 'KVĚTEN', 'ČERVEN', 'ČERVENEC', 'SRPEN', 'ZÁŘÍ', 'ŘÍJEN', 'LISTOPAD', 'PROSINEC'];

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
      () => {}
    );
  }

}
