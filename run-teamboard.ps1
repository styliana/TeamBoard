Write-Host "☕ Odpalam Twój projekt Full-Stack..." -ForegroundColor Cyan

# 1. Baza
Set-Location demo; docker-compose up -d; Set-Location ..

# 2. Backend
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd demo; taskkill /F /IM java.exe; .\mvnw spring-boot:run"

# 3. Frontend
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd teamboard-frontend; npm start"

Write-Host "🚀 Wszystko gotowe! Sprawdź http://localhost:4200" -ForegroundColor Green