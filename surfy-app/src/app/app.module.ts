import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SprBoldComponent } from './spr-bold/spr-bold.component';
import { SprBoldProduitComponent } from './spr-bold-produit/spr-bold-produit.component';
import { SprTooltipComponent } from './spr-tooltip/spr-tooltip.component';
import { HomeComponent } from './home/home.component';
import { ProduitComponent } from './produit/produit.component';
import { ProduitDetailComponent } from './produit-detail/produit-detail.component';
import { ProduitCrudRowComponent } from './produit-crud-row/produit-crud-row.component';
import { PrixCategoryPipe } from './prix-category.pipe';


//Configuration des routes
const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'produit', component: ProduitComponent },
    { path: 'produit/:id', component: ProduitDetailComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' },
];


@NgModule({
  declarations: [
    AppComponent,
    SprBoldComponent,
    SprBoldProduitComponent,
    SprTooltipComponent,
    HomeComponent,
    ProduitComponent,
    ProduitDetailComponent,
    ProduitCrudRowComponent,
    PrixCategoryPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
