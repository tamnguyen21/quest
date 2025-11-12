import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObservablePage } from './observable-page';

describe('ObservablePage', () => {
  let component: ObservablePage;
  let fixture: ComponentFixture<ObservablePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ObservablePage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ObservablePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
