import { Component, OnInit, OnDestroy } from '@angular/core';
import { faPlus, faHome, faSquare, faTimes } from '@fortawesome/free-solid-svg-icons';
import { Room } from 'src/app/models/room.model';
import { Options } from '@m0t0r/ngx-slider';
import { RestApiService } from 'src/app/services/rest-api.service';
import { interval } from 'rxjs';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent implements OnInit, OnDestroy {

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

   private timer: any

  constructor(
    private readonly restApiService: RestApiService
  ) { }

  ngOnDestroy(): void {
    this.timer.unsubscribe()
  }

  ngOnInit(): void {
    this.restApiService.getAllRoomData().subscribe(
      (roomsData: Array<Room>) => {
        this.roomsData = roomsData;
        this.selectedRoom = roomsData[0];
      },
      (error) => alert(error),
      () => {}
    );

    this.timer = interval(10000).subscribe(_x => this.updateRooms());
  }

  updateRooms(): void {
    this.restApiService.getAllRoomData()
    .subscribe(
      (roomsData: Array<Room>) => this.roomsData = roomsData,
      (error) => console.log(error),
      () => {this.onSelect(this.roomsData[this.selectedRoom.id - 1])}
    );
  }

  onSelect(room: Room): void {
    this.selectedRoom = room;
  }
}