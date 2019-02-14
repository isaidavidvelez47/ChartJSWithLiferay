import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent }  from './app.component';
import { PruebaComponent } from './prueba/prueba.component';

@NgModule({
   imports: [
      BrowserModule
   ],
   declarations: [
      AppComponent,
      PruebaComponent
   ],
	entryComponents: [AppComponent,PruebaComponent],
   bootstrap: [],
   //Donotbootstrapanything(seengDoBootstrap()below)\\r\\n\\tproviders: []
})
export class AppModule {

	// Avoid bootstraping any component statically because we need to attach to
	// the portlet's DOM, which is different for each portlet instance and,
   // thus, cannot be determined until the page is rendered (during runtime).
   
   ngDoBootstrap() {}
}