import { Component, OnInit } from '@angular/core';
import { AdService } from './services/ad.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.html',        // POPRAWIONE: bez "/services/" i z dobrą nazwą
  styleUrls: ['./app.component.css']          // POPRAWIONE: tak się nazywa Twój plik na liście
})
export class AppComponent implements OnInit {
  ads: any[] = [];

  constructor(private adService: AdService) {}

  ngOnInit(): void {
    this.adService.getAds().subscribe(data => {
      this.ads = data;
    });
  }
}
