import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamDetailsStatsComponent } from './team-details-stats.component';

describe('TeamDetailsStatsComponent', () => {
  let component: TeamDetailsStatsComponent;
  let fixture: ComponentFixture<TeamDetailsStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeamDetailsStatsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamDetailsStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
