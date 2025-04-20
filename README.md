# Trabajo-Fin-Grado
Desarrollo de una aplicación web para el seguimiento personalizado de datos de fútbol mediante una API externa

> Este trabajo consiste en una aplicación web para seguir ligas, equipos y jugadores de fútbol.  
> Permite registro/login con JWT, consultar standings, calendario de partidos, detalles de jugadores, y guardar favoritos.

## 📦 Arquitectura

- **Backend**: Spring Boot (Java 17 + Spring Data JPA + H2/MySQL + Spring Security + JWT)
- **Frontend**: Angular 19 (Standalone Components + Angular Material + Bootstrap)
- **Infraestructura**: Docker / Docker Compose / Traefik (TLS/Let’s Encrypt)

## 📖 Uso básico de los endpoints

Para usar la API, cada recurso se corresponde a un conjunto de endpoints:

### 1. Athletes (jugadores)
```bash
GET  /api/athletes/league/{league}        # Obtener jugadores por liga
GET  /api/athletes/team/{team}           # Obtener jugadores por equipo
GET  /api/athletes/{athlete}             # Obtener un jugador por su ID
```

### 2. Auth (Registro / Login)
```bash
POST /api/auth/register                  # Registrar un nuevo usuario
POST /api/auth/login                     # Iniciar sesión (retorna JWT)
```

### 3. Favoritos
#### 3.1 Ligas favoritas
```bash
POST   /api/favorites/leagues/add                # Añadir una liga favorita al usuario
DELETE /api/favorites/leagues/remove/{favoriteId}# Quitar una liga favorita
GET    /api/favorites/leagues/{userId}           # Obtener las ligas favoritas de un usuario
```
#### 3.2 Jugadores favoritos
```bash
POST   /api/favorites/players/add
DELETE /api/favorites/players/remove/{favoriteId}
GET    /api/favorites/players/{userId}
```
#### 3.3 Equipos favoritos
```bash
POST   /api/favorites/teams/add
DELETE /api/favorites/teams/remove/{favoriteId}
GET    /api/favorites/teams/{userId}
```

### 4. Ligas
```bash
GET /api/leagues                # Todas las ligas (5 grandes ligas de Europa)
GET /api/leagues/{leagueId}     # Obtener una liga por su id
```

### 5. Partidos
```bash
GET /api/matches/league/{league}     # Partidos de una liga
GET /api/matches/team/{team}         # Partidos de un equipo y temporada
GET /api/matches/{id}               # Partido por su id
GET /api/matches/team/{id}/event    # Próximo partido de un equipo
```

### 6. Temporadas
```bash
GET /api/seasons/league/{league}/team/{team}  # Lista de temporadas de un equipo
```

### 7. Clasificación
```bash
GET /api/standings                   # Clasificación de una liga
```

### 8. Estadísticas
```bash
GET /api/statistics/team/{team}      # Estadísticas de un equipo
```

### 9. Equipos
```bash
GET /api/teams/{team}                # Obtener un equipo por su id
GET /api/teams/league/{league}       # Equipos de una liga
```

### 10. Mejores jugadores
```bash
GET /api/topPerformers/team/{team}   # Mejores jugadores de un equipo (liga+temporada)
```

### 11. Fichajes
```bash
GET /api/transactions/league/{league}    # Fichajes de una liga dada una temporada
```

### 12. Usuario
```bash
GET /api/users/profile               # Datos del perfil de usuario
```

## 🔍 Estructura del proyecto

```bash
/backend
 ├─ src/
 │   └─ main/
 │       ├─ java/...           # Controladores, servicios, repositorios, entidades
 │       └─ resources/         # application.yml, esquemas de BD
 └─ Dockerfile

/frontend
 ├─ src/
 │   └─ app/
 │       ├─ features/          # home, leagues, matches, profile, favoritos…
 │       ├─ dtos/              # Interfaces TypeScript (TeamDTO, StandingDTO…)
 │       ├─ services/          # HTTP clients
 │       └─ app.component.*    # NavBar, configuración de rutas
 ├─ angular.json
 └─ Dockerfile
```

### Requisitos
- Java 17 o superior  
- Node.js 16+  
- Docker (opcional)

 



