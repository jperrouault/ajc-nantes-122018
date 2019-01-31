import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { SprBoldComponent } from './spr-bold/spr-bold.component';
import { SprBoldProduitComponent } from './spr-bold-produit/spr-bold-produit.component';
import { SprTooltipComponent } from './spr-tooltip/spr-tooltip.component';
import { HomeComponent } from './home/home.component';
import { ProduitComponent } from './produit/produit.component';


//Configuration des routes
const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'produit', component: ProduitComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' },
];


@NgModule({
  declarations: [
    AppComponent,
    SprBoldComponent,
    SprBoldProduitComponent,
    SprTooltipComponent,
    HomeComponent,
    ProduitComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
