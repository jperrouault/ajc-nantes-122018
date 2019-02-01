import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProduitCrudRowComponent } from './produit-crud-row.component';

describe('ProduitCrudRowComponent', () => {
  let component: ProduitCrudRowComponent;
  let fixture: ComponentFixture<ProduitCrudRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProduitCrudRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProduitCrudRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
