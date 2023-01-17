import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {filter, first, map, tap} from 'rxjs/operators';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { DataService } from './services/dataservice';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [HttpClient,DataService]
})
export class AppComponent implements OnInit{
  pageTitle: string
  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
	        this.router.navigate(['gestioneVeicolo'])
			this.router.events
			.pipe(
				filter((event) => event instanceof NavigationEnd),
				map(() => {
					let route: ActivatedRoute = this.router.routerState.root;
					let routeTitle = '';
					while (route!.firstChild) {
						route = route.firstChild;
					}
					if (route.snapshot.data['title']) {
						routeTitle = route!.snapshot.data['title'];
					}
					return routeTitle;
				})
			)
			.subscribe((title: string) => {
				if (title) {
					this.pageTitle = title
				}
			});
  }
  
  pages = [
		{ title: 'Gestione Veicolo', route: '/gestioneVeicolo' },
		{ title: 'Inserimento Massivo', route: '/inserimentoMassivo' }
	]

}
