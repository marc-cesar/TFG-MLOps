import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestsListPageComponent } from './requests-list-page.component';

describe('RequestsListPageComponent', () => {
  let component: RequestsListPageComponent;
  let fixture: ComponentFixture<RequestsListPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RequestsListPageComponent]
    });
    fixture = TestBed.createComponent(RequestsListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
