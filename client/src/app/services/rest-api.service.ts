import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
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
    return this.http.get<Array<Room>>(ApiManager.BACKEND_API + '/rooms');
  }

  getRoomsDataById(id: number): Observable<any>
  {
    return this.http.get<Room>(ApiManager.BACKEND_API + '/rooms/' + id);
  }

  /** PUT */
  updateRoomById(room: Room, id: number): Observable<any>
  {
    return this.http.put(ApiManager.BACKEND_API + '/rooms/' + id, room, this.httpOptions);
  }

  /** POST */
  addRoom(room: Room): Observable<any>
  {
    return this.http.post<Room>(ApiManager.BACKEND_API + '/rooms', room, this.httpOptions);
  }

  /** GET STATS */
  getLightStatsByRoomId(id: number): Observable<any>
  {
    return this.http.get(ApiManager.BACKEND_API + '/stats/weeklyLight/' + id);
  }
}
