import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Room } from '../models/room.model';
import { ApiManager } from '../api-manager';

@Injectable({
  providedIn: 'root'
})
export class RestApiService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  constructor(private http: HttpClient) { }

  getAllRoomData(): Observable<any> 
  {
    return this.http.get<Array<Room>>(ApiManager.BACKEND_API + '/rooms')
  }

  getRoomsDataById(id): Observable<any>
  {
    return this.http.get<Room>(ApiManager.BACKEND_API + '/rooms/' + id);
  }

  updateRoomById(room: Room, id): Observable<any>
  {
    console.log(room);
    return this.http.put(ApiManager.BACKEND_API + '/rooms/' + id, room, this.httpOptions);
  }

  updateRoom(room: Room): Observable<any>
  {
    console.log(room);
    return this.http.put(ApiManager.BACKEND_API + '/rooms', room);
  }
}
