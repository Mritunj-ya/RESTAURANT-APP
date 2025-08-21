import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  template: `
    <main style="padding:24px">
      <h1>OneBanc - MEAN Client</h1>
      <ul><li *ngFor="let c of cuisines">{{ c.name }}</li></ul>
    </main>
  `
})
export class AppComponent implements OnInit {
  cuisines: any[] = [];
  constructor(private http: HttpClient) {}
  ngOnInit() {
    this.http.get('http://localhost:8080/api/cuisines').subscribe((r:any)=> this.cuisines = r, ()=>{});
  }
}
