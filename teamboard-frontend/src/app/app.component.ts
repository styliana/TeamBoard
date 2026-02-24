import { Component, OnInit } from '@angular/core';
import { AdService } from './services/ad.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
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
