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
  newAd = { title: '', description: '', category: '' };

  constructor(private adService: AdService) {}

  ngOnInit() {
    const storedUser = localStorage.getItem('coffee_user');
    if (storedUser) {
      this.currentUser = storedUser;
      this.loadAds();
    }
  }

  loadAds() {
    this.adService.getAds().subscribe({
      next: (data: any[]) => {
        this.ads = data.map(ad => ({
          ...ad,
          participantNames: ad.participantNames || []
        }));
      },
      error: (err) => {
        if (err.status === 401) {
          this.logout(); // Wyrzuca, jeśli sesja wygasła lub jest błędna
        }
      }
    });
  }

  saveAd() {
    // .trim() usuwa białe znaki z początku i końca, żeby nikt nie wpisał 10 spacji
    const title = this.newAd.title?.trim() || '';
    const desc = this.newAd.description?.trim() || '';
    const cat = this.newAd.category?.trim() || '';

    // Walidacja spójna z backendem (AdRequestDTO)
    if (title.length < 3 || desc.length < 10 || cat.length === 0) {
      alert('Tytuł min. 3 znaki, Opis min. 10 znaków, a miasto nie może być puste!');
      return;
    }

    const payload = {
      title: title,
      description: desc,
      category: cat
    };

    this.adService.addAd(payload).subscribe({
      next: () => {
        this.newAd = { title: '', description: '', category: '' }; // Najpierw czyścimy formularz
        this.loadAds(); // Potem odświeżamy listę, a Angular od razu to narysuje!
      },
      error: (err) => {
        console.error('Błąd zapisu:', err);
        if (err.status === 401) alert('Błąd (401): Brak dostępu. Zaloguj się ponownie.');
        else if (err.status === 400) alert('Błąd (400): Serwer odrzucił dane (zła długość).');
        else alert(`Błąd serwera (${err.status}). Sprawdź logi!`);
      }
    });
  }

  handleAuth(name: string, pass: string) {
    if (!name || !pass) return;
    if (this.isRegisterMode) {
      this.adService.register({username: name, password: pass}).subscribe({
        next: () => { alert('Zarejestrowano! Teraz się zaloguj.'); this.isRegisterMode = false; },
        error: (err) => alert('Błąd: ' + (err.error?.message || 'Zły login'))
      });
    } else {
      this.setIdentity(name, pass);
    }
  }

  setIdentity(name: string, pass: string) {
    const authString = 'Basic ' + btoa(`${name}:${pass}`);

    // Kluczowa zmiana: Pytamy serwer czy dane są dobre PRZED ustawieniem użytkownika
    this.adService.getAdsWithAuth(authString).subscribe({
      next: (data: any[]) => {
        // Serwer autoryzował zapytanie, wpuszczamy użytkownika
        localStorage.setItem('coffee_auth', authString);
        localStorage.setItem('coffee_user', name);
        this.currentUser = name;
        this.ads = data.map(ad => ({ ...ad, participantNames: ad.participantNames || [] }));
      },
      error: (err) => {
        // Serwer odrzucił logowanie
        alert('Zły login lub hasło!');
        this.logout();
      }
    });
  }

  markAsPresent(ad: any) {
    if (ad.author === this.currentUser || ad.participantNames?.includes(this.currentUser)) return;
    this.adService.joinAd(ad.id).subscribe({ next: () => this.loadAds() });
  }

  removeAd(adId: number) {
    if (confirm('Usunąć?')) {
      this.adService.deleteAd(adId).subscribe({ next: () => this.loadAds() });
    }
  }

  logout() {
    localStorage.clear();
    this.currentUser = '';
    this.ads = [];
  }

  trackByAdId(index: number, ad: any): number { return ad.id; }
}
