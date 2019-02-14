import { Chart } from 'chart.js';
import { Component, Input, OnInit } from '@angular/core';
import { StatisticsService } from '../services/statistics.service';

@Component({
  selector: 'app-bar-chart',
  styleUrls: ['./bar-chart.component.css'],
  templateUrl: './bar-chart.component.html'
})
export class BarChartComponent implements OnInit {
  @Input() barChartData;

  BarChart: Chart;

  constructor(private _statistics: StatisticsService) {
    this._statistics.configBarObservable.subscribe(data => {
      this.setBarGraphic(data);
    });
  }

  ngOnInit() {
    this.setBarGraphic(this.barChartData);
  }

  setBarGraphic(data) {
    // Le borra el caché de la gráfica que estaba anteriormente
    if (this.BarChart) {
      this.BarChart.destroy();
    }

    this.BarChart = new Chart('barChart', {
      type: 'bar',
      data: {
        // Lista los 4 fondos
        labels: this.getLabelsForBarGraphic(data.data),
        datasets: [{
          label: 'Rentabilidad',
          // Hacer los calculos del precio por las rentabiilidades
          data: this.getDataForGraphic(data.price, data.data),
          backgroundColor: [
            'rgba(255, 212, 0, 0.6)',
            'rgba(255, 138, 54, 0.6)',
            'rgba(123, 191, 78, 0.6)',
            'rgba(75, 126, 216, 0.6)',
          ],
          borderColor: [
            'rgba(224, 169, 0, 1)',
            'rgba(209, 110, 43, 1)',
            'rgba(99, 152, 62, 1)',
            'rgba(60, 100, 172, 1)',
          ],
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: data.title,
          display: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: false
            }
          }]
        }
      }
    });
  }

  getLabelsForBarGraphic(data) {
    return [
      'Proteccion',
      'Porvenir',
      'Oldmutual',
      'Colfondos',
    ];
  }

  getDataForGraphic(price, dates) {
    const array = [
      parseInt(price, 10) + (price * (this.random() / 100)),
      parseInt(price, 10) + (price * (this.random() / 100)),
      parseInt(price, 10) + (price * (this.random() / 100)),
      parseInt(price, 10) + (price * (this.random() / 100))
    ];
    return array;
  }

  random(): number {
    const num: number = Math.floor(Math.random() * (500 - 0) + 100) / 100;
    return num;
  }
}
