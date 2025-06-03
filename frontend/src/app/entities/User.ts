export class User {
  id?: number;         // Opcional, se asigna automáticamente en el backend
  username: string;    // Corresponde al "username" de la entidad
  email: string;       // Corresponde al "email"
  password: string;    // Corresponde al "password"
  favoritePlayers?: FavoritePlayer[]; // Opcional: Lista de jugadores favoritos
  favoriteTeams?: FavoriteTeam[]; // Opcional: Lista de equipos favoritos
  favoriteLeagues?: FavoriteLeague[]; // Opcional: Lista de ligas favoritas
  profileImageUrl?: string; // URL de la imagen de perfil del usuario

  constructor(username: string, email: string, password: string) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
}

export class FavoritePlayer {
  id: string;     
  playerId: string;

  constructor(id: string, playerId: string) {
    this.id = id;
    this.playerId = playerId;
    
  }
}

export class FavoriteTeam {
  id: string;
  teamId: string;

  constructor(id: string, teamId: string) {
    this.id = id;
    this.teamId = teamId;
  }
}

export class FavoriteLeague {
  id: string;
  leagueId: string;

  constructor(id: string, leagueId: string) {
    this.id = id;
    this.leagueId = leagueId;
  }
}
