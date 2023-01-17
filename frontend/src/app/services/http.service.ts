import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { tap } from 'rxjs/operators'

@Injectable({
    providedIn: 'root'
})
export class HttpService {

    constructor(private http: HttpClient) { }

    baseUrl: string = 'http://localhost:8080/api/'

    callGetOutside(url: string) {
        return this.http.get(url).pipe(tap(
            (data: any) => { },
            (error: any) => { },
            () => { }
        ))
    }

    callGet(url: string) {
        return this.http.get(this.baseUrl + url).pipe(tap(
            (data: any) => { },
            (error: any) => { },
            () => { }
        ))
    }
    callPut(url: string, item: any) {
        return this.http.put(this.baseUrl + url,item).pipe(tap(
            (data: any) => { },
            (error: any) => { },
            () => { }
        ))
    }
    
    callDelete(url: string) {
        return this.http.delete(this.baseUrl + url).pipe(tap(
            (data: any) => { },
            (error: any) => { },
            () => { }
        ))
    }
    callPost(url: string, item: any) {
        return this.http.post(this.baseUrl + url, item).pipe(tap(
            (data: any) => { },
            (error: any) => { },
            () => { }
        ))
    }

}
