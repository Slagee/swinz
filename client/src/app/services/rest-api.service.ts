import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Room } from '../models/room.model';
import { ApiManager } from '../api-manager';

@Injectable({
  providedIn: 'root'
})
export class RestApiService {

  constructor(private http: HttpClient) { }

  getAllRoomData(): Observable<any> 
  {
    return this.http.get<Array<Room>>(ApiManager.BACKEND_API + '/rooms')
  }

  getRoomsDataById(id): Observable<any>
  {
    return this.http.get<Array<Room>>(ApiManager.BACKEND_API + '/rooms/' + id);
  }
}
