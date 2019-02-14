import { NgModule } from '@angular/core'
import { RouterModule } from '@angular/router';
import { rootRouterConfig } from './app.routes';
import { AppComponent } from './app.component';
import { GithubService } from './github/shared/github.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';

import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { SimulatorComponent } from './simulator/simulator.component';
import { FormComponent } from './form/form.component';


import { StatisticsService } from './services/statistics.service';
import { TagComponent } from './tags/tag.component';
import { TableComponent } from './table/table.component';
import { BarChartComponent } from './barChart/bar-chart.component';
import { LineChartComponent } from './lineChart/line-chart.component';

@NgModule({
  declarations: [
    AppComponent,
    SimulatorComponent,
    FormComponent,
    TagComponent,
    TableComponent,
    BarChartComponent,
    LineChartComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(rootRouterConfig, { useHash: true })
  ],
  providers: [
    GithubService,
    StatisticsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
