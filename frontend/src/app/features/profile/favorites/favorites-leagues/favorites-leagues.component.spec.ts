import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoritesLeaguesComponent } from './favorites-leagues.component';

describe('FavoritesLeaguesComponent', () => {
  let component: FavoritesLeaguesComponent;
  let fixture: ComponentFixture<FavoritesLeaguesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FavoritesLeaguesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FavoritesLeaguesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
