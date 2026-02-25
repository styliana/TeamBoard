import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AdService {
  private apiUrl = 'http://localhost:8080/api/ads';

  // Dane do logowania (zakodowane w Base64)
  private authHeader = 'Basic ' + btoa('olunia:kawa2026');

  constructor(private http: HttpClient) {}

  private getHeaders() {
    return new HttpHeaders({
      'Authorization': this.authHeader
    });
  }

  getAds(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  addAd(ad: any): Observable<any> {
    return this.http.post(this.apiUrl, ad, { headers: this.getHeaders() });
  }

  joinAd(id: number): Observable<any> {
    return this.http.patch(`${this.apiUrl}/${id}/join`, {}, { headers: this.getHeaders() });
  }
}
