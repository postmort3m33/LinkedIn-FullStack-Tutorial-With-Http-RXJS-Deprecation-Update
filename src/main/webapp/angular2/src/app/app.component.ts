import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private http: HttpClient) {
  }

  private baseUrl: string = 'http://localhost:8080';
  public submitted: boolean = false;
  roomsearch: FormGroup = new FormGroup([]);
  rooms: Room[] = [];
  currentCheckInVal: string = "";
  currentCheckOutVal: string = "";
  request: ReserveRoomRequest | undefined;

  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl('')
    });

    // Subscribe to changes in checkin and checkout
    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    roomsearchValueChanges$.subscribe(valChange => {
      this.currentCheckInVal = valChange.checkin;
      this.currentCheckOutVal = valChange.checkout;
    }
    )
  }

  onSubmit({ value, valid }: { value: Roomsearch, valid: boolean }) {

    this.getAll()
      .subscribe(
        rooms => this.rooms = rooms
      );
  }

  reserveRoom(value: string) {

    this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal)

    this.createReservation(this.request);

  }

  getAll(): Observable<Room[]> {
    return this.http
      .get<{ content: Room[] }>(this.baseUrl + '/room/reservation/v1?checkin=' + this.currentCheckInVal + '&checkout=' + this.currentCheckOutVal)
      .pipe(map(response => response.content));
  }

  createReservation(body: Object) {
    let bodyString = JSON.stringify(body);
    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    console.log(body);

    this.http.post(this.baseUrl + '/room/reservation/v1' , body, {headers: headers})
    .subscribe(res => console.log(res));
  }
}

export interface Roomsearch {
  checkin: string;
  checkout: string;
}

export interface Room {
  id: string;
  roomNumber: string;
  price: string;
  links: string;
}

export class ReserveRoomRequest {
  roomId: string;
  checkin: string;
  checkout: string;

  constructor(roomId: string,
              checkin: string,
              checkout: string) {

    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }

}


