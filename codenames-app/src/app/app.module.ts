import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { CaseComponent } from './case/case.component';
import { PlateauComponent } from './plateau/plateau.component';
import { ChatComponent } from './chat/chat.component';
import { HomeComponent } from './home/home.component';


//Configuration des routes
const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'plateau/:id', component: PlateauComponent },
    { path: 'plateau/:id/:espion', component: PlateauComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];



@NgModule({
    declarations: [
        AppComponent,
        CaseComponent,
        PlateauComponent,
        ChatComponent,
        HomeComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        RouterModule.forRoot(routes)
    ],
    providers: [],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
