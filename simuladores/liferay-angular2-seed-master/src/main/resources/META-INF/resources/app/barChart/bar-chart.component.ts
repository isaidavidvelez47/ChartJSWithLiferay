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
  fondos = [];
  lowerPrice: any;

  constructor(private _statistics: StatisticsService) {
    this._statistics.configBarObservable.subscribe(data => {
      this.loadRentabilidades(data);
    });
  }

  ngOnInit() {
    this.loadRentabilidades(this.barChartData);
  }

  loadRentabilidades(data) {
    this._statistics.getTableRents(data.idProjection, data.data.finalDate).subscribe(fondo => {
      this.fondos = [];
      for (const key in fondo) {
        if (fondo.hasOwnProperty(key)) {
          this.add(
            fondo[key].fondo, // El nombre del fondo
            (fondo[key].rentabilidad * 100), // La rentabilidad con todos sus decimales
            data.price); // el precio
        }
      }
      this.setBarGraphic(this.fondos, data.textBar);
    });
  }

  add(name, fullRent, price) {
    // Mostramos en pantalla solo el la rentabilidad con dos decimales, pero en el fondo
    // multiplicamos el precio por la full rentabilidad con todos sus decimales
    this.fondos.push(
      {
        name,
        rent: fullRent,
        price: parseInt(price, 10) + (price * (fullRent / 100))
      }
    );
  }

  setBarGraphic(data, title) {
    // Le borra el caché de la gráfica que estaba anteriormente
    if (this.BarChart) {
      this.BarChart.destroy();
    }

    this.BarChart = new Chart('barChart', {
      type: 'horizontalBar',
      data: {
        labels: [],
        datasets: this.getDataForBarGraphic(data),
      },
      options: {
        title: {
          text: title,
          display: true
        },
        scales: {
          xAxes: [{
            gridLines: {
              display: false,
              drawBorder: false
            },
            ticks: {
              display: false,
              beginAtZero: false
            }
          }],
          yAxes: [{
            gridLines: {
              display: false,
              drawBorder: true
            },
            ticks: {
              display: true,
              beginAtZero: false
            },
            barPercentage: 0.6
          }]
        },
        tooltips: {
          enabled: true
        }
      }
    });
  }

  getDataForBarGraphic(data) {
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

    data.forEach((fondo, index) => {
      vec.push(
        this.createLabel(
          fondo.name,
          fondo.price,
          backgrounds[index],
          borders[index],
          1,
          false, data
        )
      );
    });

    return vec;
  }

  createLabel(label, price, background, borderColor, borderWidth, fill, data) {
    return {
      label: label,
      // Enlista toda la linea de tiempo de puntos del fondo
      data: [price],
      backgroundColor: this.gradient(background, this.getPercent(price, data)),
      hoverBackgroundColor: this.gradient(background, this.getPercent(price, data)),
      borderColor: 'rgba(0, 0, 0, .0)',
      borderWidth: borderWidth,
      fill: fill
    };
  }


  getPercent(price, data) {
    let higherPrice = 0;
    let lowerPrice = 999999999999999;
    this.lowerPrice = 0;

    data.forEach(fondo => {
      if (higherPrice < fondo.price) {
        higherPrice = fondo.price;
      }
    });

    data.forEach(fondo => {
      if (lowerPrice > fondo.price) {
        lowerPrice = fondo.price;
      }
    });
    this.lowerPrice = lowerPrice;


    higherPrice = higherPrice - lowerPrice;
    price = price - lowerPrice;

    const percent = ((price * 100) / higherPrice);

    if (percent <= 5) {
      return 60;
    } else if (percent <= 10) {
      return 90 - percent;
    } else if (percent <= 15) {
      return 120 - percent;
    } else if (percent <= 20) {
      return 140 - percent;
    } else if (percent <= 25) {
      return 150 - percent;
    } else if (percent <= 30) {
      return 160 - percent;
    } else if (percent <= 35) {
      return 175 - percent;
    } else if (percent <= 40) {
      return 200 - percent;
    } else if (percent <= 45) {
      return 225 - percent;
    } else if (percent <= 50) {
      return 250 - percent;
    } else if (percent <= 55) {
      return 275 - percent;
    } else if (percent <= 60) {
      return 300 - percent;
    } else if (percent <= 65) {
      return 325 - percent;
    } else if (percent <= 70) {
      return 350 - percent;
    } else if (percent <= 75) {
      return 375 - percent;
    } else if (percent <= 80) {
      return 400 - percent;
    } else if (percent <= 85) {
      return 425 - percent;
    } else if (percent <= 90) {
      return 450 - percent;
    } else if (percent <= 95) {
      return 475 - percent;
    } else {
      return 500 - percent;
    }
  }

  gradient(color, percent) {
    const canvas = (document.getElementById('barChart') as HTMLCanvasElement);
    const ctx = canvas.getContext('2d');
    const gradient = ctx.createLinearGradient(percent, 0, 0, 0);
    gradient.addColorStop(0, color);
    gradient.addColorStop(1, 'rgba(255, 255, 255, 0.0');
    return gradient;
  }
}
