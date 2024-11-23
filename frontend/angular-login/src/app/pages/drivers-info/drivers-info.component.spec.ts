import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DriversInfoComponent } from './drivers-info.component';

describe('DriversInfoComponent', () => {
  let component: DriversInfoComponent;
  let fixture: ComponentFixture<DriversInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DriversInfoComponent]
    });
    fixture = TestBed.createComponent(DriversInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
