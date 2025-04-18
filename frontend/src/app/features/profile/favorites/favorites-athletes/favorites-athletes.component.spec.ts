import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoritesAthletesComponent } from './favorites-athletes.component';

describe('FavoritesAthletesComponent', () => {
  let component: FavoritesAthletesComponent;
  let fixture: ComponentFixture<FavoritesAthletesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FavoritesAthletesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FavoritesAthletesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
