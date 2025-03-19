export class User {
    id?: number;       // Opcional, depende de tu backend si se asigna automáticamente
    nombre: string;
    email: string;
    password: string;
  
    constructor(nombre: string, email: string, password: string) {
      this.nombre = nombre;
      this.email = email;
      this.password = password;
    }
}