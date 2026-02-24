Write-Host "☕ Parzę TeamBoard... Chwileczkę!" -ForegroundColor Brown

# 1. Uruchomienie bazy danych w Dockerze
Write-Host "🐘 Odpalam PostgreSQL w Dockerze..." -ForegroundColor Cyan
Set-Location "$PSScriptRoot\demo"
docker-compose up -d
Set-Location $PSScriptRoot

# 2. Uruchomienie Backend (Spring Boot)
Write-Host "🍃 Odpalam Spring Boot (Backend)..." -ForegroundColor Green
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$PSScriptRoot\demo'; .\mvnw spring-boot:run"

# 3. Uruchomienie Frontend (Angular)
Write-Host "🅰️ Odpalam Angular (Frontend)..." -ForegroundColor Red
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$PSScriptRoot\teamboard-frontend'; npm start"

Write-Host "✅ Skrypty startowe wysłane! " -ForegroundColor Yellow
Write-Host "🌐 Frontend będzie pod: http://localhost:4200"
Write-Host "⚙️ Backend API pod: http://localhost:8080/api/ads"