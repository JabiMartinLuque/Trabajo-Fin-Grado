import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AthleteDetailsComponent } from './athlete-details.component';

describe('AthleteDetailsComponent', () => {
  let component: AthleteDetailsComponent;
  let fixture: ComponentFixture<AthleteDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AthleteDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AthleteDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
