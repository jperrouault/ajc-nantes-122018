import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SprBoldProduitComponent } from './spr-bold-produit.component';

describe('SprBoldProduitComponent', () => {
  let component: SprBoldProduitComponent;
  let fixture: ComponentFixture<SprBoldProduitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SprBoldProduitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SprBoldProduitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
