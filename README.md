# CodeArena

A full-stack online coding platform built using:

- React
- Spring Boot
- MySQL
- Docker

## Prerequisites

- Git
- Docker Desktop

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/karthic-23/CodeArena.git
cd CodeArena
```

### 2. Create .env

Windows:

```powershell
copy .env.example .env
```

Linux / Mac:

```bash
cp .env.example .env
```

### 3. Start the application

```bash
docker compose up -d
```

### 4. Open the application

Frontend:

http://localhost:3000

Backend:

http://localhost:8080

## Stop the application

```bash
docker compose down
```

## Reset the database

```bash
docker compose down -v
docker compose up -d
```

The database will automatically be recreated using:

```text
database/codearena_full.sql
```