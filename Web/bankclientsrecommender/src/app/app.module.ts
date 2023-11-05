import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProductComponent } from './components/product/product.component';
import { NgOptimizedImage } from '@angular/common';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { RequestsTableComponent } from './components/requests/requests-table/requests-table.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    NavigationBarComponent,
    RequestsTableComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgOptimizedImage,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
