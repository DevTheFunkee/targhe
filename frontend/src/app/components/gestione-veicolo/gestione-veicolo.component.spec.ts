import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestioneVeicoloComponent } from './gestione-veicolo.component';

describe('GestioneVeicoloComponent', () => {
  let component: GestioneVeicoloComponent;
  let fixture: ComponentFixture<GestioneVeicoloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestioneVeicoloComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestioneVeicoloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
