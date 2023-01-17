import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';

@Component({
	selector: 'app-alert-modal',
	templateUrl: './alert-modal-confirm.component.html',
	styleUrls: ['./alert-modal-confirm.component.css']
})
export class AlertModalConfirmationComponent implements OnInit {

	title: string
	text: string
	textColor: string

	constructor(public bsModalRef: BsModalRef) { }

	ngOnInit() { }

	confirm() {
		if (this.bsModalRef.content.callback != null) {
			this.bsModalRef.content.callback('si');
			this.bsModalRef.hide();
		}
	}

	decline() {
		if (this.bsModalRef.content.callback != null) {
			this.bsModalRef.content.callback('no');
			this.bsModalRef.hide();
		}
	}
}
