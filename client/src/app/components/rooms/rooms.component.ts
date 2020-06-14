import { Component, OnInit } from '@angular/core';
import { faPlus, faHome, faSquare, faTimes } from '@fortawesome/free-solid-svg-icons';
import { Room } from 'src/app/models/room.model';
import { Options } from '@m0t0r/ngx-slider';
import { RestApiService } from 'src/app/services/rest-api.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent implements OnInit {

  faPlus = faPlus;
  faHome = faHome;
  faSquare = faSquare;
  faTimes = faTimes;

  sliderValue: number = 23.0;
  sliderOptions: Options = {
    floor: 15,
    ceil: 30,
    step: 0.1
  }

  roomsData: Array<Room> = [];
  selectedRoom: Room;

  constructor(
    private readonly restApiService: RestApiService
  ) { }

  ngOnInit(): void {
    this.restApiService.getAllRoomData()
    .subscribe(
      (roomsData: Array<Room>) => {
        this.roomsData = roomsData;
        this.selectedRoom = roomsData[0]},
      (error) => console.log(error),
      () => {}
    );
  }

  onSelect(room: Room): void {
    this.selectedRoom = room;
    console.log(room.name);
  }
}