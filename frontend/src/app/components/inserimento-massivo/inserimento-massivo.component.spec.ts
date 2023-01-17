import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InserimentoMassivoComponent } from './inserimento-massivo.component';

describe('InserimentoMassivoComponent', () => {
  let component: InserimentoMassivoComponent;
  let fixture: ComponentFixture<InserimentoMassivoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InserimentoMassivoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InserimentoMassivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
