import { Component } from '@angular/core';
import { faHome, faUserCircle, faList, faSignOutAlt } from '@fortawesome/free-solid-svg-icons';
import { from } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'client';
  faHome = faHome;
  faUserCircle = faUserCircle;
  faList = faList;
  faSignOutAlt = faSignOutAlt;
}
