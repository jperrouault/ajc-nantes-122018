import { TestBed } from '@angular/core/testing';

import { PlateauService } from './plateau.service';

describe('PlateauService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PlateauService = TestBed.get(PlateauService);
    expect(service).toBeTruthy();
  });
});
