import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamDetailsNavbarComponent } from './team-details-navbar.component';

describe('TeamDetailsNavbarComponent', () => {
  let component: TeamDetailsNavbarComponent;
  let fixture: ComponentFixture<TeamDetailsNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeamDetailsNavbarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamDetailsNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
