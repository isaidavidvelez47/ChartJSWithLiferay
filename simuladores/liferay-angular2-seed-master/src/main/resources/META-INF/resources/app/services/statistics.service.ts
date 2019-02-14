import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class StatisticsService {

  rafpea: string;
  rafp: string;

  configTableObservable = new Subject<any>();
  configBarObservable = new Subject<any>();
  configLineObservable = new Subject<any>();

  constructor(private _http: Http) {
    this.rafpea = 'http://10.125.65.97:3000/rafpea';
    this.rafp = 'http://10.125.65.97:3000/rafp';
  }

  getOpcionesInversion() {
    return this._http.get('http://localhost:8080/o/api-simulador/cesantias/opciones-inversion')
      .map(result => JSON.parse(result['_body']));
  }

  getLinealRents(idProjection: string) {
    return this._http.get(`http://localhost:8080/o/api-simulador/cesantias/graficos/rentabilidades?proyeccion=${idProjection}`)
      .map(result => JSON.parse(result['_body']));
  }

  getTableRents(idProjection: string, finalDate: string) {
    return this._http.get(`http://localhost:8080/o/api-simulador/cesantias/rentabilidades?proyeccion=${idProjection}&periodo=${finalDate}`)
      .map(result => JSON.parse(result['_body']));
  }

  barConfig(val) {
    this.configBarObservable.next(val);
  }

  lineConfig(val) {
    this.configLineObservable.next(val);
  }

  tableConfig(val) {
    this.configTableObservable.next(val);
  }
}
