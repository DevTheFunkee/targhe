import { NgModule,LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxSpinnerModule } from "ngx-spinner";
import { AppComponent } from './app.component';
import { HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ExceptionInterceptor } from './services/exception.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { MatTableModule } from "@angular/material/table";
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { InserimentoMassivoComponent } from './components/inserimento-massivo/inserimento-massivo.component';
import { GestioneVeicoloComponent } from './components/gestione-veicolo/gestione-veicolo.component';
@NgModule({
  declarations: [
    AppComponent,
    InserimentoMassivoComponent,
    GestioneVeicoloComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgxSpinnerModule,
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    PaginationModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatTableModule
  ],
  providers: [
	{ provide: HTTP_INTERCEPTORS, useClass: ExceptionInterceptor, multi: true },
    { provide: LOCALE_ID, useValue: 'it-IT' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
