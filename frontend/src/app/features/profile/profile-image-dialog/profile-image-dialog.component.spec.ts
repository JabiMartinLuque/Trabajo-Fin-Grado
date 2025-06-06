import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileImageDialogComponent } from './profile-image-dialog.component';

describe('ProfileImageDialogComponent', () => {
  let component: ProfileImageDialogComponent;
  let fixture: ComponentFixture<ProfileImageDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileImageDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileImageDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
