export class User {
  id?: number;         // Opcional, se asigna automáticamente en el backend
  username: string;    // Corresponde al "username" de la entidad
  email: string;       // Corresponde al "email"
  password: string;    // Corresponde al "password"
  role?: string;       // Opcional: Rol del usuario (ADMIN, USER)

  constructor(username: string, email: string, password: string, role?: string) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
  }
}
