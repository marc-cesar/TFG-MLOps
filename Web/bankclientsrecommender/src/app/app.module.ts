import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProductComponent } from './components/product/product.component';
import { NgOptimizedImage } from '@angular/common';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { RequestsTableComponent } from './components/requests/requests-table/requests-table.component';
import { NewRequestPageComponent } from './new-request-page/new-request-page.component';
import { RouterModule, Routes } from '@angular/router';
import { RequestsListPageComponent } from './requests-list-page/requests-list-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { FooterCreditsComponent } from './footer-credits/footer-credits.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ResponseDialogComponent } from './response-dialog/response-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'Requests', component: RequestsListPageComponent},
  { path: 'NewRequest', component: NewRequestPageComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    NavigationBarComponent,
    RequestsTableComponent,
    NewRequestPageComponent,
    RequestsListPageComponent,
    HomePageComponent,
    FooterCreditsComponent,
    ResponseDialogComponent,
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    NgOptimizedImage,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }