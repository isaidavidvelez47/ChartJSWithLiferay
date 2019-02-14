import { Component, OnInit, Input } from '@angular/core';
import { StatisticsService } from '../services/statistics.service';

@Component({
  selector: 'app-table',
  styleUrls: ['./table.component.css'],
  templateUrl: './table.component.html'
})
export class TableComponent implements OnInit {

  @Input() tableData;
  titleRent = '';
  titlePrice = '';

  fondos = [];

  constructor(private _statistics: StatisticsService) {
    this._statistics.configTableObservable.subscribe(data => {
      this.setTableValues(data);
      this.loadRentabilidades(data);
    });
  }

  ngOnInit() {
    this.setTableValues(this.tableData);
    this.loadRentabilidades(this.tableData);
  }

  setTableValues(data) {
    this.setTitle(data);
  }

  setTitle(data) {
    if (data.textButton.trim() === 'Corto Plazo (3 Meses)') {
      this.titleRent = 'Rentabilidad EA 3 meses';
      this.titlePrice = 'Saldo Final Inversión - 3 meses';
    } else if (data.textButton.trim() === 'Largo Plazo (24 Meses)') {
      this.titleRent = 'Rentabilidad EA 24 meses';
      this.titlePrice = 'Saldo Final Inversión - 24 meses';
    } else if (data.textButton.trim() === 'Largo Plazo vs Corto Plazo') {
      this.titleRent = 'Rentabilidad EA';
      this.titlePrice = 'Saldo Final Inversión';
    }
  }

  add(name, fullRent, decimalrent, price) {
    // Mostramos en pantalla solo el la rentabilidad con dos decimales, pero en el fondo
    // multiplicamos el precio por la full rentabilidad con todos sus decimales
    this.fondos.push(
      {
        name,
        rent: decimalrent,
        price: parseInt(price, 10) + (price * (fullRent / 100))
      }
    );
  }

  random(): number {
    const num: number = Math.floor(Math.random() * (500 - 0) + 100) / 100;
    return num;
  }

  loadRentabilidades(data) {
    this._statistics.getTableRents(data.idProjection, data.dates.finalDate).subscribe(fondo => {
      this.fondos = [];
      for (const key in fondo) {
        if (fondo.hasOwnProperty(key)) {
          this.add(
            fondo[key].fondo, // El nombre del fondo
            (fondo[key].rentabilidad * 100), // La rentabilidad con todos sus decimales
            parseFloat((fondo[key].rentabilidad * 100).toString()).toFixed(2), // La rentabilidad con 2 decimales
            data.price); // el precio
        }
      }
    });
  }
}
