import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AdService {
  private apiUrl = 'http://localhost:8080/api/ads';
  private authUrl = 'http://localhost:8080/api/register';

  constructor(private http: HttpClient) {}

  private getHeaders() {
    const auth = localStorage.getItem('coffee_auth');
    return {
      headers: new HttpHeaders({
        'Authorization': auth ? auth : '',
        'Content-Type': 'application/json'
      })
    };
  }

  getAds(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl, this.getHeaders());
  }

  addAd(ad: any): Observable<any> {
    return this.http.post(this.apiUrl, ad, this.getHeaders());
  }

  joinAd(id: number): Observable<any> {
    return this.http.patch(`${this.apiUrl}/${id}/join`, {}, this.getHeaders());
  }

  // Metoda do rejestracji
  register(user: any): Observable<any> {
    return this.http.post(this.authUrl, user);
  }
}
