import { Chart } from 'chart.js';
import { Component, Input, OnInit } from '@angular/core';
import { StatisticsService } from '../services/statistics.service';

@Component({
  selector: 'app-line-chart',
  styleUrls: ['./line-chart.component.css'],
  templateUrl: './line-chart.component.html'
})
export class LineChartComponent implements OnInit  {

  @Input() lineChartData;
  LineChart: Chart;

  constructor(private _statistics: StatisticsService) {
    this._statistics.configLineObservable.subscribe(data => {
      this.getRentPointsFromService(data);
    });
  }

  ngOnInit() {
    this.getRentPointsFromService(this.lineChartData);
  }

  getRentPointsFromService(data) {
    this._statistics.getLinealRents(data.idProjection).subscribe(res => {
      this.setLineGraphic(res, data);
    });
  }

  setLineGraphic(data, selectedOption) {
    // Le borra el caché de la gráfica que estaba anteriormente
    if (this.LineChart) {
      this.LineChart.destroy();
    }

    this.LineChart = new Chart('lineChart', {
      type: 'line',
      data: {
        // Lista los 4 fondos
        labels: this.getLabelsForLineGraphic(data.profitability),
        // Pinta en la gráfica todos los puntitos en sus respectivos meses
        datasets: this.getDataForLineGraphic(data, selectedOption.idProjection),
      },
      options: {
        title: {
          text: selectedOption.textLine,
          display: true
        },
        scales: {
          xAxes: [{
            gridLines: {
              display: false,
              drawBorder: true
            }
          }],
          yAxes: [{
            gridLines: {
              display: true,
              drawBorder: true
            },
            ticks: {
              beginAtZero: false
            }
          }]
        }
        /*scales: {
          yAxes: [{
            ticks: {
              min: 0,
              max: 10,
              stepSize: 1
            }
          }]
        }*/
      }
    });
  }

  getLabelsForLineGraphic(data) {
    const vec = [];
    // lista todos los labels que en este caso son todas las fechas finales
    // disponibles dependiendo del periodo seleccionado
    data.forEach(i => {
      vec.push(i.finalDate);
    });
    return vec;
  }

  getDataForLineGraphic(data, idProjection) {
    const backgrounds = [
      '#D8126A',
      '#005276',
      '#33A300',
      '#C99648'
    ];

    const borders = [
      '#D8126A',
      '#005276',
      '#33A300',
      '#C99648'
    ];

    const vec = [];

    let minifiedText = '';

    if (idProjection === 'CORTO_LARGO_PLAZO_24M') {
      minifiedText = 'colfondos';
    }

    data.labels.forEach((fondo, index) => {
      vec.push(
        this.createLabel(
          fondo,
          this.simplifyText(minifiedText + fondo),
          backgrounds[index],
          borders[index],
          2,
          false,
          0.2,
          data
        )
      );
    });
    return vec;
  }

  simplifyText(text: string): string {
    if (text.includes('colfondos')) {
      text = text.replace(/\ /g, '');
    } else {
      text = text.trim();
      text = text.toLowerCase();
      text = text.replace(/\ /g, '');
    }
    return text;
  }

  createLabel(label, name, background, borderColor, borderWidth, fill, lineTension, data) {
    return {
      label: label,
      // Enlista toda la linea de tiempo de puntos del fondo
      data: this.getPoints(data.profitability, name),
      backgroundColor: background,
      borderColor: borderColor,
      borderWidth: borderWidth,
      fill: fill,
      lineTension: lineTension,
    };
  }

  gradient(color) {
    const canvas = (document.getElementById('lineChart') as HTMLCanvasElement);
    const ctx = canvas.getContext('2d');
    const gradient = ctx.createLinearGradient(0, 0, 0, 573);
    gradient.addColorStop(0, color);
    gradient.addColorStop(1, 'rgba(255, 255, 255, 0.0');
    return gradient;
  }

  getPoints(data, fondo) {
    const vec = [];
    data.forEach(element => {
      vec.push(element.percent[fondo] * 100);
    });
    return vec;
  }

  random(): number {
    const num: number = Math.floor(Math.random() * (500 - 0) + 100) / 100;
    return num;
  }
}
