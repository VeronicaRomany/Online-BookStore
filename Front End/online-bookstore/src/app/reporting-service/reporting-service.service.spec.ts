import { TestBed } from '@angular/core/testing';

import { ReportingServiceService } from './reporting-service.service';

describe('ReportingServiceService', () => {
  let service: ReportingServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportingServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
