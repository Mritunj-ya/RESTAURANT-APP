# OneBanc Full-Stack Bundle

This repository bundle includes:
- `original_android_project/` - your original Android Studio project (OneBancRestaurant).
- `mern_app/` - MERN-ready skeleton with:
  - `server/` - Node.js + Express API (run `npm install` then `npm run dev`).
  - `client/` - React starter (run `npm install` then `npm start`).
- `mean_app/` - MEAN-ready skeleton with:
  - `server/` - same Node API as in MERN.
  - `client_angular/` - minimal Angular starter (you need Angular CLI to run).
- `docker-compose.yml` - launches MongoDB for local development.

## How to run (server)
1. Start MongoDB: `docker compose up -d`
2. Start server: `cd mern_app/server && npm install && npm run dev`
3. Seed sample data: `node seed.js` (or `npm run seed`)
4. Start client (React): `cd mern_app/client && npm install && npm start`

For Angular client, install Angular CLI and run from `mean_app/client_angular`.

> The original Android project is left intact in `original_android_project/`. Update `ApiClient.kt` BASE_URL to `http://<your-host>:8080/` (or `10.0.2.2` for emulator).

