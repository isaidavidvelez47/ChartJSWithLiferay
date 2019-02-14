import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'form-app',
  styleUrls: ['./form.component.css'],
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
  @Input() options;
  @Output() calcular = new EventEmitter();

  // Calendariooo
  showCalendar = false;
  availableYears = [];
  months = [];

  activeDateList = [];
  graphData = [];

  // Campos del formulario
  price: string;
  year: number;
  month: number;
  initialDate: string;
  finalDate: string;
  activeButton: string;
  textTable: string;
  textBar: string;
  textLine: string;
  idProjection: string;

  constructor() { }

  ngOnInit() {
    this.year = 2019;
    this.resetMonths();
  }

  resetMonths() {
    this.months = [
      { number: '01', name: 'ene', active: false },
      { number: '02', name: 'feb', active: false },
      { number: '03', name: 'mar', active: false },
      { number: '04', name: 'abr', active: false },
      { number: '05', name: 'may', active: false },
      { number: '06', name: 'jun', active: false },
      { number: '07', name: 'jul', active: false },
      { number: '08', name: 'ago', active: false },
      { number: '09', name: 'sep', active: false },
      { number: '10', name: 'oct', active: false },
      { number: '11', name: 'nov', active: false },
      { number: '12', name: 'dic', active: false },
    ];
  }

  setActiveOption(e) {
    this.initialDate = '';
    this.finalDate = '';
    this.activeButton = e.textButton;
    this.textTable = e.textTable;
    this.textBar = e.textBar;
    this.textLine = e.textLine;
    this.activeDateList = e.list;
    this.idProjection = e.idProjection;
    this.graphData = [];
    this.getAvailableYears();
  }

  getAvailableYears() {
    this.availableYears = [];
    this.activeDateList.forEach(date => {
      const fullDate = date.finalDate.split('/');
      // Se compara si el año ya existe en el arreglo que se muestra en el calendario,
      // para evitar repeticiones
      if (!this.yearExistOnArray(fullDate[2])) {
        // Si el año no existe, se agrega a la lista disponible
        this.availableYears.push({ year: fullDate[2] });
        this.year = fullDate[2];
        this.getMonthsByYear(this.year);
      }
    });
  }

  yearExistOnArray(year) {
    let exist = false;
    this.availableYears.forEach(date => {
      if (date.year === year) {
        exist = true;
      }
    });
    return exist;
  }

  openCalendar() {
    this.showCalendar = !this.showCalendar;
  }

  changeYear(clickNext) {
    let done = false;
    if (clickNext) {
      for (let i = 0; i < this.availableYears.length; i++) {
        if (this.year === this.availableYears[i].year && i !== (this.availableYears.length - 1) && !done) {
          this.year = this.availableYears[i + 1].year;
          done = true;
        }
      }
    } else {
      for (let i = 0; i < this.availableYears.length; i++) {
        if (this.year === this.availableYears[i].year && i !== 0 && !done) {
          this.year = this.availableYears[i - 1].year;
          done = true;
        }
      }
    }
    this.getMonthsByYear(this.year);
  }

  getMonthsByYear(year) {
    this.resetMonths();
    this.activeDateList.forEach(date => {
      const fullDate = date.finalDate.split('/');
      if (fullDate[2] === year) {
        this.months.forEach(month => {
          if (month.number === fullDate[1]) {
            month.active = true;
          }
        });
      }
    });
  }

  selectMonth(month) {
    this.activeDateList.forEach(date => {
      if (date.finalDate.substring(3) === month.number + '/' + this.year) {
        this.setFinalDate(date);
        this.showCalendar = false;
      }
    });
  }

  setFinalDate(e) {
    this.initialDate = this.formatDateToSpanish(e.initialDate);
    this.finalDate = this.formatDateToSpanish(e.finalDate);
    this.graphData = e;
  }

  formatDateToSpanish(date): string {
    const months = [
      'Enero',
      'Febrero',
      'Marzo',
      'Abril',
      'Mayo',
      'Junio',
      'Julio',
      'Agosto',
      'Septiembre',
      'Octubre',
      'Noviembre',
      'Diciembre'];

    // dividir 24/04/2018 en ['24', '04', '2018']
    const dateArray = date.split('/');

    // Toma el valor del mes '04'
    const month = dateArray[1];

    // retorna el mes + el año => Abril 2018
    return (months[month - 1] + ' ' + dateArray[2]);
  }

  calculate() {
    if (this.price === undefined) {
      alert('falta el precio');
    } else if (this.textTable === undefined) {
      alert('falta el texto de la tabla');
    } else if (this.textBar === undefined) {
      alert('falta el texto de la grafica');
    } else if (this.graphData.length === 0) {
      alert('faltan los datos de la grafica');
    } else if (this.activeDateList.length === 0) {
      alert('faltan las fechas');
    } else {
      const data = {
        // Precio de la gráfica
        price: this.price.replace(/\./g, ''),
        // Texto para la table
        textTable: this.textTable,
        // Texto para las graficas de barras
        textBar: this.textBar,
        // Texto para las graficas lineales
        textLine: this.textLine,
        // datos del grafico (fecha final y fecha inicial)
        dates: this.graphData,
        // Texto del botón
        textButton: this.activeButton,
        // idProjection
        idProjection: this.idProjection,
        // lista de fechas del periodo seleccionado para la tabla de puntos
        activeDateList: this.activeDateList
      };
      this.calcular.emit({ event: event, data: data });
    }
  }

  formatPrice() {
    this.price = this.price.replace(/\./g, '');
    const array = [];
    let cont = 0;
    for (let i = 1; i <= this.price.length; i++) {
      if (cont === 3) {
        array.push({ 'val': '.' });
        cont = 0;
      }
      array.push({ 'val': this.price.charAt(this.price.length - i) });
      cont++;
    }
    this.price = '';
    for (let i = 1; i <= array.length; i++) {
      this.price = this.price + array[array.length - i].val;
    }
  }
}
