import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SprTooltipComponent } from './spr-tooltip.component';

describe('SprTooltipComponent', () => {
  let component: SprTooltipComponent;
  let fixture: ComponentFixture<SprTooltipComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SprTooltipComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SprTooltipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
