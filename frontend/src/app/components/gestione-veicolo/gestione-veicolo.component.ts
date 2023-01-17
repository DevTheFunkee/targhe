import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../services/http.service'
import { FormBuilder, Validators } from '@angular/forms'
import { AlertModalComponent } from '../alert-modal/alert-modal.component'
import { BsModalService, BsModalRef, ModalOptions } from 'ngx-bootstrap/modal';
import { AlertModalConfirmationComponent } from '../alert-modal-confirmation/alert-modal-confirm.component'
import { DataService } from '../../services/dataservice';
import * as _ from 'lodash'
@Component({
  selector: 'app-gestione-veicolo',
  templateUrl: './gestione-veicolo.component.html',
  styleUrls: ['./gestione-veicolo.component.css']
})
export class GestioneVeicoloComponent implements OnInit {
    isActive: any = false
	veicoliForm: any
	veicoli: any = []
	newVeicolo: any = {}
	modalRef: BsModalRef;
	array: string[]
 constructor(private httpService: HttpService, private formBuilder: FormBuilder, private modalService: BsModalService,public dataservice: DataService) {
		this.veicoliForm = this.formBuilder.group({
			veicoliRows: this.formBuilder.array([])
		})
	}

  ngOnInit(): void {
	  this.getListaVeicoli()
	  this.array = this.dataservice.array
  }
	getListaVeicoli() {
		this.httpService.callGet('veicolo').subscribe(
			(data: any) => {
				this.veicoli = _.filter(data, function(o) { return o.id !== 1 })
				this.createVeicoliForm();
			},
			(error: any) => { },
			() => { }
		)
	}

	saveModVeicolo(index: number) {
		var row = this.veicoliForm.controls.veicoliRows.controls[index].value
		row.appUrl = window.location.origin
		this.httpService.callPut("veicolo", row).subscribe(
			(data: any) => {
				this.veicoli[index] = data
			},
			(error: any) => { },
			() => { }
		)
	}

	eliminaVeicolo(index: any) {
		this.modalRef = this.modalService.show(AlertModalConfirmationComponent, {
			initialState: {
				text: 'Sicuri di voler eliminare l istituto dalla lista?',
				title: "Attenzione!",
				textColor: 'text-success',
				callback: (result) => {
					if (result == 'si') {
						let url = 'veicolo?id=' + this.veicoli[index].id
						this.httpService.callDelete(url).subscribe(
							(data: any) => {
								this.veicoli.splice(index, 1)
							},
							(error: any) => { },
							() => { }
						)
					}
				}
			}
		})
}



	createVeicoliForm() {
		this.veicoliForm.controls.veicoliRows = this.formBuilder.array(
			this.veicoli.map(x => this.formBuilder.group({
				id: [x.id],
				targa: [x.targa, [Validators.required,Validators.pattern('^[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}$')]],
				tipo: [x.tipo, [Validators.required,Validators.pattern('^[AMR]$')] ],
				proprietario: [x.proprietario, [Validators.required,Validators.pattern('^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$')]],
				dataImmatricolazione: [x.dataImmatricolazione, [Validators.required]],
				cilindrata: [x.cilindrata, [Validators.required]]
			}))
		)
	}

	inserisciVeicolo() {
		this.httpService.callPost('veicolo', this.newVeicolo).subscribe(
			(data: any) => {
				if (data.message != null){
					this.openErrorModal(data.message)
				}else{
				this.veicoli.push(data)
				this.veicoliForm.controls.veicoliRows.push(this.formBuilder.group({
					id: [data.id],
					targa: [data.targa, [Validators.required,Validators.pattern('^[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}$')]],
					tipo: [data.tipo, [Validators.required,Validators.pattern('^[AMR]$')] ],
					proprietario: [data.proprietario, [Validators.required,Validators.pattern('^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$')]],
					dataImmatricolazione: [data.dataImmatricolazione, [Validators.required]],
					cilindrata: [data.cilindrata, [Validators.required]]
				}));
				}
			},
			(error: any) => { },
			() => { }
		)
	}

	cancelModVeicolo(index: number) {
		delete this.veicoli[index].inModifica
		this.veicoliForm.controls.veicoliRows.controls[index].setValue({
			id: this.veicoli[index].id,
			targa: this.veicoli[index].targa,
			tipo: this.veicoli[index].tipo,
			proprietario: this.veicoli[index].proprietario,
			dataImmatricolazione: this.veicoli[index].dataImmatricolazione,
			cilindrata: this.veicoli[index].cilindrata
		})
	}

	formInvalid(index: number) {
		return this.veicoliForm.controls.veicoliRows.controls[index].pristine ||
			this.veicoliForm.controls.veicoliRows.controls[index].status === 'INVALID'
	}
	openModal(text: string) {
		const initialState = {
			textColor: 'text-success',
			title: "Ben fatto!",
			text: text
		}
		this.modalService.show(AlertModalComponent, { initialState })
	}
	
		openErrorModal(text: string) {
		const initialState = {
			textColor: 'text-error',
			title: "Problema!",
			text: text
		}
		this.modalService.show(AlertModalComponent, { initialState })
	}
}
