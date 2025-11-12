import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpPage } from './http-page';

describe('HttpPage', () => {
  let component: HttpPage;
  let fixture: ComponentFixture<HttpPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HttpPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
