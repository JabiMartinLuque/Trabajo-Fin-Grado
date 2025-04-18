import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoritesTeamsComponent } from './favorites-teams.component';

describe('FavoritesTeamsComponent', () => {
  let component: FavoritesTeamsComponent;
  let fixture: ComponentFixture<FavoritesTeamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FavoritesTeamsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FavoritesTeamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
