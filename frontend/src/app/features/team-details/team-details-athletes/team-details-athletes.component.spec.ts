import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamDetailsAthletesComponent } from './team-details-athletes.component';

describe('TeamDetailsAthletesComponent', () => {
  let component: TeamDetailsAthletesComponent;
  let fixture: ComponentFixture<TeamDetailsAthletesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeamDetailsAthletesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamDetailsAthletesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
