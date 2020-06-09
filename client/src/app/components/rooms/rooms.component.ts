import { Component, OnInit } from '@angular/core';
import { faPlus, faHome, faSquare, faTimes } from '@fortawesome/free-solid-svg-icons';

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

  constructor() { }

  ngOnInit(): void {
  }

}
