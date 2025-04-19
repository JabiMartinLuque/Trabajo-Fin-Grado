# Trabajo-Fin-Grado
Desarrollo de una aplicación web para el seguimiento personalizado de datos de fútbol mediante una API externa

> Este trabajo consiste en una aplicación web para seguir ligas, equipos y jugadores de fútbol.  
> Permite registro/login con JWT, consultar standings, calendario de partidos, detalles de jugadores, y guardar favoritos.

## 📦 Arquitectura

- **Backend**: Spring Boot (Java 17 + Spring Data JPA + H2/MySQL + Spring Security + JWT)
- **Frontend**: Angular 19 (Standalone Components + Angular Material + Bootstrap)
- **Infraestructura**: Docker / Docker Compose / Traefik (TLS/Let’s Encrypt)

## 📖 Uso básico de los endpoints

1. **Athletes (jugadores)**

- GET /api/athletes/league/{league} - Obtener jugadores por liga.
- GET /api/athletes/team/{team} - Obtener jugadores por equipo.
- GET /api/athletes/{athlete} - Obtener jugadores por id de jugador.

2. **Auth (Registro / Login)**

- POST /api/auth/register - Para registrar a un nuevo usuario.

- POST /api/auth/login ⇒ JWT - Para iniciar sesión.

3. **Favoritos**

3.1 Ligas favoritas
- POST /api/favorites/leagues/add - Para añadir una liga favorita al usuario.
- DELETE /api/favorites/leagues/remove/{favoriteId} - Para quitar una liga favorita al usuario.
- GET /api/favorites/leagues/{userId} - Para obtener las ligas favoritas del usuario.

3.2 Jugadores favoritos
- POST /api/favorites/players/add - Para añadir un jugador favorito al usuario.
- DELETE /api/favorites/players/remove/{favoriteId} - Para quitar un jugador favorito al usuario.
- GET /api/favorites/players/{userId} - Para obtener los jugadores favoritos del usuario.

3.3 Equipos favoritos
- POST /api/favorites/teams/add - Para añadir un equipo favorito al usuario.
- DELETE /api/favorites/teams/remove/{favoriteId} - Para quitar un equipo favorito al usuario.
- GET /api/favorites/teams/{userId} - Para obtener los equipos favoritos del usuario.

4. **Ligas**

- GET /api/leagues - Para obtener todas las ligas (en este caso las 5 grandes ligas de Europa).
- GET /api/leagues/{leagueId} - Para obtener una liga por su id

5. **Partidos**

- GET /api/matches/league/{league} -  Para obtener los partidos por liga
- GET /api/matches/team/{team} - Para obtener los partidos por equipo y temporada
- GET /api/matches/{id} - Para obtener un partido por su id
- GET/api/matches/team/{id}/event - Para obtener el proximo partido de un equipo

6. **Temporadas**

- GET /api/seasons/league/{league}/team/{team} - Para obtener la lista de temporadas de un equipo

7. **Clasificación**

- GET /api/standings - Para obtener la clasificación de una liga

8. **Estadísiticas**

- GET /api/statistics/team/{team} - Para obtener las estadísticas de un equipo

9. **Equipos**

- GET /api/teams/{team} - Para obtener un equipo por su id
- GET /api/teams/league/{league} - Para obtener los equipos de una liga

10. **Mejores jugadores**

- GET /api/topPerformers/team/{team} - Para obtener los mejores jugadores por equipo dada una liga y temporada

11. **Fichajes**

- GET /api/transactions/league/{league} - Para obtener los fichajes de una liga dada una temporada

12. **Usuario**

- GET /api/users/profile - Para obtener los datos del perfil del usuario

## 🔍 Estructura del proyecto

```bash
/backend
 ├─ src/
 │   └─ main/
 │       ├─ java/...           # Controladores servicios, repositorios, entidades
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

 



