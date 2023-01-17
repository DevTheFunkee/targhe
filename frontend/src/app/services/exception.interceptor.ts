import { Injectable } from '@angular/core'
import { HttpErrorResponse, HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http'
import { AlertModalComponent } from '../components/alert-modal/alert-modal.component'
import { Observable } from 'rxjs'
import { catchError, finalize  } from 'rxjs/operators'
import { BsModalService } from 'ngx-bootstrap/modal'
import { Router } from '@angular/router'
import { NgxSpinnerService } from 'ngx-spinner'

@Injectable()
export class ExceptionInterceptor implements HttpInterceptor {

    constructor(private modalService: BsModalService, private router: Router, private spinner: NgxSpinnerService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        this.spinner.show()
        return next.handle(request).pipe(
            catchError((error: HttpErrorResponse) => {
                        let errorMsg = '';
                    if (error.error instanceof ErrorEvent) {
                        console.log('This is client side error');
                        errorMsg = `Error: ${error.error.message}`;
                    } else {
                        console.log('This is server side error');
                        errorMsg = `${error.error}`;
                    }
                    this.openErrorModal(errorMsg)
                
                return next.handle(request)
            }),
            finalize(() => {
                this.spinner.hide()
            })
        )
    }

    hideSpinner(request: HttpRequest<any>) {
        this.spinner.hide()
        return request
    }

    openErrorModal(cause: string) {
        const initialState = {
            title: "Si è verificato un errore",
            text: cause
        }
        this.modalService.show(AlertModalComponent, { initialState })
    }

    openServerOffModal() {
        const initialState = {
            title: "Attenzione",
            text: "Il server non risponde"
        }
        this.modalService.show(AlertModalComponent, { initialState })
    }
}
