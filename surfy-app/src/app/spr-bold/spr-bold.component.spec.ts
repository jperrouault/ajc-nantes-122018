import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SprBoldComponent } from './spr-bold.component';

describe('SprBoldComponent', () => {
  let component: SprBoldComponent;
  let fixture: ComponentFixture<SprBoldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SprBoldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SprBoldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
