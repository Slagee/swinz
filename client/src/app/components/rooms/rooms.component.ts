import { Component, OnInit, OnDestroy } from '@angular/core';
import { faPlus, faHome, faSquare, faCheckSquare, faWindowClose } from '@fortawesome/free-solid-svg-icons';
import { Options } from '@m0t0r/ngx-slider';
import { RestApiService } from 'src/app/services/rest-api.service';
import { interval } from 'rxjs';
import { Room } from 'src/app/models/room.model';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent implements OnInit, OnDestroy {

  faPlus = faPlus;
  faHome = faHome;
  faSquare = faSquare;
  faWindowClose = faWindowClose;
  faCheckSquare = faCheckSquare;

  roomsData: Array<Room> = [];
  selectedRoom: Room;
  userRoom = new Room();

  sliderValue: number = 21;
  sliderOptions: Options = {
    floor: 15,
    ceil: 30,
    step: 0.1
  }

  private timer: any

  constructor(
    private readonly restApiService: RestApiService
  ) { }

  ngOnDestroy(): void {
    this.timer.unsubscribe()
  }

  ngOnInit(): void {
    this.restApiService.getAllRoomData().subscribe(
      (rooms: Array<Room>) => {
        this.roomsData = rooms;
      },
      (error: any) => console.log(error),
      () => {
        this.onSelect(this.roomsData[0]);
        this.sortRooms();
      }
    );

    this.timer = interval(10000).subscribe(_x => this.updateRooms());
    this.userRoom.selectedTemperature = 21.0;
    
  }

  onSubmit(): void {
    this.restApiService.addRoom(this.userRoom).subscribe(
      (room: Room) => this.roomsData.push(room),
      (error: any) => console.log(error),
      () => { this.updateRooms(); }
    );
  }

  updateRooms(): void {
    this.restApiService.getAllRoomData().subscribe(
      (rooms: Array<Room>) => this.roomsData = rooms,
      (error: any) => console.log(error),
      () => {
        this.onSelect(this.roomsData.find(x => x.id == this.selectedRoom.id));
        this.sortRooms();
      }
    );
  }

  sliderChange(): void {
    this.selectedRoom.selectedTemperature = this.sliderValue;
    this.restApiService.updateRoomById(this.selectedRoom, this.selectedRoom.id).subscribe(  
          () => { this.updateRooms }
        );
  }

  onSelect(room: Room): void {
    this.selectedRoom = room;
    this.sliderValue = this.selectedRoom.selectedTemperature;
    this.restApiService.getLightStatsByRoomId(this.selectedRoom.id).subscribe(
      (x: number) => this.selectedRoom.lightWeeklyConsumption = x,
      () => {}
    );
  }

  radiatorCheck() {
    this.selectedRoom.radiatorState = !this.selectedRoom.radiatorState;
    this.restApiService.updateRoomById(this.selectedRoom, this.selectedRoom.id).subscribe(
      () => { this.updateRooms() }
    );
  }

  onChange() {
    if (this.userRoom.selectedTemperature < 15) {
      this.userRoom.selectedTemperature = 15;
    } else if (this.userRoom.selectedTemperature > 30) {
      this.userRoom.selectedTemperature = 30;
    }
  }

  sortRooms() {
    this.roomsData = this.roomsData.sort((a, b) => a.id - b.id);
  }
}