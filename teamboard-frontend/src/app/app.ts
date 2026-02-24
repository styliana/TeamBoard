import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Naprawia błąd NG8103 (*ngFor)
import { RouterOutlet } from '@angular/router';
import { AdService } from './services/ad.service'; // Twój kelner od danych

@Component({
  selector: 'app-root',
  standalone: true,
  // Dodajemy CommonModule, żeby Angular rozumiał pętle i warstwy
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.html',
  styleUrls: ['./app.component.css']
})
export class App implements OnInit {
  // Definiujemy zmienną ads - to naprawia błąd TS2339
  ads: any[] = [];

  // Wstrzykujemy serwis w konstruktorze
  constructor(private adService: AdService) {}

  // Ta funkcja odpala się przy starcie strony
  ngOnInit(): void {
    this.adService.getAds().subscribe({
      next: (data) => {
        this.ads = data;
        console.log('Kawusia podana! Dane z bazy:', data);
      },
      error: (err) => {
        console.error('Błąd przy parzeniu kawy (backend nie odpowiada):', err);
      }
    });
  }
}
