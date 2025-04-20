import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamDetailsMatchesComponent } from './team-details-matches.component';

describe('TeamDetailsMatchesComponent', () => {
  let component: TeamDetailsMatchesComponent;
  let fixture: ComponentFixture<TeamDetailsMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeamDetailsMatchesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamDetailsMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
