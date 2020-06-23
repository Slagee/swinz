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
        this.selectedRoom = this.roomsData[0];
      },
      (error) => console.log(error),
      () => {this.sliderValue = this.selectedRoom.selectedTemperature}
    );

    this.timer = interval(1000).subscribe(_x => this.updateRooms());
  }

  onSubmit(): void {
    console.log("submit");
  }

  updateRooms(): void {
    this.restApiService.getAllRoomData().subscribe(
      (rooms: Array<Room>) => this.roomsData = rooms,
      (error) => console.log(error),
      () => {
        this.onSelect(this.roomsData.find(x => x.id == this.selectedRoom.id));
        this.sliderValue = this.selectedRoom.selectedTemperature}
    );
  }

  newRoom(): void {
    this.restApiService.addRoom({ name: "Test3", currentTemperature: 20, selectedTemperature: 25, powerConsumption: 33, radiatorState: true, lightState: false } as unknown as Room).subscribe(
      (room) => this.roomsData.push(room),
      (error) => console.log(error),
      () => {this.updateRooms()}
    );
  }

  sliderChange(): void {
    this.selectedRoom.selectedTemperature = this.sliderValue;
    this.restApiService.updateRoomById(this.selectedRoom, this.selectedRoom.id)
        .subscribe(
          () => {}
        );
  }

  onSelect(room: Room): void {
    this.selectedRoom = room;
  }

  getCurrentRoom() {
    return JSON.stringify(this.userRoom);
  }
}