import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // 1. Potrzebne do obsługi formularzy
import { AdService } from './services/ad.service';

@Component({
  selector: 'app-root',
  standalone: true,
  // 2. Dodajemy FormsModule tutaj, by naprawić błąd NG8002
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrls: ['./app.component.css']
})
export class App implements OnInit {
  ads: any[] = [];

  // 3. Tożsamość użytkownika (naprawia błędy o 'currentUser')
  currentUser: string = '';

  // 4. Obiekt dla formularza (naprawia błędy o 'newAd')
  newAd = {
    title: '',
    description: '',
    category: '',
    author: ''
  };

  constructor(private adService: AdService) {}

  ngOnInit() {
    // Sprawdzamy, czy użytkownik jest już w pamięci przeglądarki
    const savedUser = localStorage.getItem('coffee_user');
    if (savedUser) {
      this.currentUser = savedUser;
    }
    this.loadAds();
  }

  loadAds() {
    this.adService.getAds().subscribe(data => {
      this.ads = data;
    });
  }

  // 5. Metoda do ustawiania imienia (naprawia błąd 'setIdentity')
  setIdentity(name: string) {
    if (name.trim()) {
      this.currentUser = name;
      localStorage.setItem('coffee_user', name);
    }
  }

  // 6. Wylogowanie (naprawia błąd 'logout')
  logout() {
    this.currentUser = '';
    localStorage.removeItem('coffee_user');
  }

  // 7. Zapisywanie ogłoszenia (naprawia błąd 'saveAd')
  saveAd() {
    if (this.newAd.title && this.newAd.description) {
      this.newAd.author = this.currentUser; // Automatycznie przypisz autora
      this.adService.addAd(this.newAd).subscribe(() => {
        this.loadAds(); // Odśwież listę po dodaniu
        this.newAd = { title: '', description: '', category: '', author: '' }; // Wyczyść form
      });
    }
  }

  // 8. Logika przycisku obecności (naprawia błąd 'markAsPresent')
  markAsPresent(ad: any) {
    ad.userJoined = !ad.userJoined;
  }
}
