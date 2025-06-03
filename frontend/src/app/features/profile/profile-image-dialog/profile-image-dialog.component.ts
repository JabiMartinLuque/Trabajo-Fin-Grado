import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { ProfileService } from '../profile.service';
import { UserStateService } from '../userState.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-profile-image-dialog',
  imports: [MatDialogModule, CommonModule],
  templateUrl: './profile-image-dialog.component.html',
  styleUrl: './profile-image-dialog.component.scss'
})
export class ProfileImageDialogComponent {
  selectedImage: File | null = null;
  previewUrl: string | ArrayBuffer | null = null;

  constructor(
    private profileService: ProfileService,
    private userStateService: UserStateService,
    public dialogRef: MatDialogRef<ProfileImageDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { imageUrl: string, username: string },
    private snackBar: MatSnackBar
  ) {
    this.previewUrl = this.data.imageUrl;
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.selectedImage = file;
      const reader = new FileReader();
      reader.onload = e => this.previewUrl = reader.result;
      reader.readAsDataURL(file);
    }
  }

  onSave(): void {
    if (this.selectedImage) {
      const userId = Number(localStorage.getItem('userId'));
      this.profileService.uploadProfileImage(userId, this.selectedImage).subscribe({
        next: (imageUrl) => {
          // Actualizamos el estado del usuario con la nueva imagen
          const currentUser = this.userStateService.getUser();
          if (currentUser) {
            currentUser.profileImageUrl = imageUrl;
            this.userStateService.setUser(currentUser);
          }
          this.snackBar.open('Imagen actualizada exitosamente', '', { duration: 3000 });
          this.dialogRef.close(imageUrl);
        },
        error: (err) => {
          console.error('Error al subir la imagen', err);
          this.snackBar.open('Error al actualizar la imagen', '', { duration: 3000 });
          this.dialogRef.close();
        }
      });
    } else {
      this.dialogRef.close();
    }
  }

  onDelete(): void {
    const currentUser = this.userStateService.getUser();
    if (!currentUser || !(currentUser.profileImageUrl || '').trim().length) {
      this.snackBar.open('No hay imagen para eliminar', '', { duration: 3000 });
      this.dialogRef.close();
      return;
    }

    const userId = Number(localStorage.getItem('userId'));
    this.profileService.deleteProfileImage(userId).subscribe({
      next: () => {
        // Actualizamos el estado del usuario para eliminar la imagen
        currentUser.profileImageUrl = '';
        this.userStateService.setUser(currentUser);
        this.snackBar.open('Imagen eliminada exitosamente', '', { duration: 3000 });
        this.dialogRef.close(null);
      },
      error: (err) => {
        console.error('Error al eliminar la imagen', err);
        this.snackBar.open('Error al eliminar la imagen', '', { duration: 3000 });
        this.dialogRef.close();
      }
    });
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
