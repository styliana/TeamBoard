import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdService {
  private apiUrl = 'http://localhost:8080/api/ads';

  constructor(private http: HttpClient) { }

  getAds(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
