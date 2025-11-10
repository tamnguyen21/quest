import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AscBold } from './asc-bold';

describe('AscBold', () => {
  let component: AscBold;
  let fixture: ComponentFixture<AscBold>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AscBold]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AscBold);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
