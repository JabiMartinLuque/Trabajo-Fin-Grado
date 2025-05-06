import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamDetailsTopPerformersComponent } from './team-details-top-performers.component';

describe('TeamDetailsTopPerformersComponent', () => {
  let component: TeamDetailsTopPerformersComponent;
  let fixture: ComponentFixture<TeamDetailsTopPerformersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeamDetailsTopPerformersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamDetailsTopPerformersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
