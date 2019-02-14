import { Component, OnInit } from "@angular/core";
import { StatisticsService } from '../services/statistics.service';
import { Chart } from "chart.js";

@Component({
  selector: "simulator",
  styleUrls: ["./simulator.component.css"],
  templateUrl: "./simulator.component.html"
})
export class SimulatorComponent implements OnInit {

  // Todas las listas del JSON
  longTerm = [];
  shortTerm = [];

  // Listas para el formulario
  options = [];

  // Grafico
  btnCalculateHasBeenClicked = false;
  barChartData: any;
  lineChartData: any;
  tableData: any;

  constructor(private _statistics: StatisticsService) { }

  ngOnInit() {
    this.getOpcionesInversion();
  }

  getOpcionesInversion() {
    this._statistics.getOpcionesInversion().subscribe(res => {
      for (const key in res) {
        if (res.hasOwnProperty(key)) {
          this.options.push(
            {
              textButton: res[key].options.nameProjection,
              idProjection: res[key].options.idProjection,
              textTable: res[key].tittle[0],
              textBar: res[key].tittle[1],
              textLine: res[key].tittle[2],
              list: this.getDateList(res[key].periods)
            }
          );
        }
      }
    });
  }

  getDateList(list) {
    const vec = [];
    for (let i = list.length - 1; i >= 0; i--) {
      vec.push(list[i]);
    }
    return vec;
  }

  setBarGraphic(price, data, idProjection, textBar) {
    this.barChartData = {
      price: price,
      data: data,
      idProjection: idProjection,
      textBar: textBar
    };
    this._statistics.barConfig(this.barChartData);
  }

  setLineGraphic(idProjection, textLine) {
    this.lineChartData = {
      idProjection: idProjection,
      textLine: textLine
    };
    this._statistics.lineConfig(this.lineChartData);
  }

  setTableData(price, dates, idProjection, textButton, textTable) {
    this.tableData = {
      price: price,
      dates: dates,
      idProjection: idProjection,
      textButton: textButton,
      title: textTable
    };
    this._statistics.tableConfig(this.tableData);
  }

  calcular($event) {
    this.btnCalculateHasBeenClicked = false;
    // Pasa como parametro el precio, el nombre de el grafico y la data (labels y rentabilidades)
    this.setBarGraphic(
      $event.data.price,
      $event.data.dates,
      $event.data.idProjection,
      $event.data.textBar
    );

    // Pasa como parametro toda las fechas disponibles de acuerdo al periodo seleccionado
    this.setLineGraphic(
      $event.data.idProjection,
      $event.data.textLine
    );
    this.setTableData(
      $event.data.price,
      $event.data.dates,
      $event.data.idProjection,
      $event.data.textButton,
      $event.data.textTable
    );
    this.btnCalculateHasBeenClicked = true;
  }
}