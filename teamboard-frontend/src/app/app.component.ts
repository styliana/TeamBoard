import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdService } from './services/ad.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrls: ['./app.component.css']
})
export class App implements OnInit {
  ads: any[] = [];
  currentUser: string = '';
  newAd = { title: '', description: '', category: '', author: '' };

  constructor(private adService: AdService) {}

  ngOnInit() {
    this.currentUser = localStorage.getItem('coffee_user') || '';
    this.loadAds();
  }

  loadAds() {
    this.adService.getAds().subscribe(data => this.ads = data);
  }

  setIdentity(name: string) {
    if (name.trim()) {
      this.currentUser = name;
      localStorage.setItem('coffee_user', name);
    }
  }

  logout() {
    this.currentUser = '';
    localStorage.removeItem('coffee_user');
  }

  saveAd() {
    if (this.newAd.title && this.newAd.description) {
      this.newAd.author = this.currentUser;
      this.adService.addAd(this.newAd).subscribe(() => {
        this.loadAds();
        this.newAd = { title: '', description: '', category: '', author: '' };
      });
    }
  }

  markAsPresent(ad: any) {
    if (ad.author === this.currentUser) return;
    this.adService.joinAd(ad.id).subscribe(() => {
      this.loadAds();
      ad.userJoined = true;
    });
  }
}
