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
  isRegisterMode: boolean = false;
  newAd = { title: '', description: '', category: '', author: '' };

  constructor(private adService: AdService) {}

  ngOnInit() {
    this.currentUser = localStorage.getItem('coffee_user') || '';
    if (this.currentUser) {
      this.loadAds();
    }
  }

  loadAds() {
    this.adService.getAds().subscribe({
      next: (data) => this.ads = data,
      error: (err) => console.error('Błąd pobierania danych:', err)
    });
  }

  handleAuth(name: string, pass: string) {
    if (!name || !pass) return;

    if (this.isRegisterMode) {
      this.adService.register({username: name, password: pass}).subscribe({
        next: () => {
          alert('Zarejestrowano pomyślnie! Teraz możesz się zalogować.');
          this.isRegisterMode = false;
        },
        error: (err) => alert('Błąd: ' + (err.error?.message || 'Nieudana rejestracja'))
      });
    } else {
      this.setIdentity(name, pass);
    }
  }

  setIdentity(name: string, pass: string) {
    const authString = 'Basic ' + btoa(`${name}:${pass}`);
    localStorage.setItem('coffee_auth', authString);
    localStorage.setItem('coffee_user', name);
    this.currentUser = name;
    this.loadAds();
  }

  logout() {
    this.currentUser = '';
    localStorage.removeItem('coffee_user');
    localStorage.removeItem('coffee_auth');
    this.ads = [];
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
    if (ad.author === this.currentUser || ad.alreadyJoined) return;

    this.adService.joinAd(ad.id).subscribe({
      next: (updatedAd) => {
        // Aktualizujemy tylko jeden element na liście, zamiast przeładowywać całość
        const index = this.ads.findIndex(a => a.id === updatedAd.id);
        if (index !== -1) {
          this.ads[index] = { ...updatedAd, alreadyJoined: true };
        }
      },
      error: (err) => console.error('Błąd:', err)
    });
  }

  // Zapobiega niszczeniu i tworzeniu od nowa elementów DOM (naprawia "skakanie")
  trackByAdId(index: number, ad: any): number {
    return ad.id;
  }
}
