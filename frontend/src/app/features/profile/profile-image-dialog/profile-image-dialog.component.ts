import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { ProfileService } from '../profile.service';

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
    public dialogRef: MatDialogRef<ProfileImageDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { imageUrl: string, username: string }
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
      // Supongamos que tienes el userId disponible (puede pasarse en data)
      const userId = Number(localStorage.getItem('userId'));
      this.profileService.uploadProfileImage(userId, this.selectedImage).subscribe({
        next: (imageUrl) => this.dialogRef.close(imageUrl),
        error: (err) => {
          console.error('Error al subir la imagen', err);
          this.dialogRef.close();
        }
      });
    } else {
      // Si no se selecciona imagen, solo se cierra el diálogo
      this.dialogRef.close();
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
