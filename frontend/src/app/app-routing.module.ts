import { NgModule } from '@angular/core'
import { Routes, RouterModule } from '@angular/router'
import { GestioneVeicoloComponent } from './components/gestione-veicolo/gestione-veicolo.component'
import { InserimentoMassivoComponent } from './components/inserimento-massivo/inserimento-massivo.component'
const routes: Routes = [
 { path: 'gestioneVeicolo', component: GestioneVeicoloComponent ,data: { title: 'Gestione Veicolo' }},
 { path: 'inserimentoMassivo', component: InserimentoMassivoComponent,data: { title: 'Inserimento Massivo' } },
]

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
