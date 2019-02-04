import { Injectable } from '@angular/core';
import { Produit } from './produit/produit';
import { AppConfigService } from './app-config.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ProduitService {
    public produits: Array<Produit> = new Array<Produit>();
    public produitsAsync: any = null;
    private httpOptions: any;


    constructor(private httpClient: HttpClient, private appConfig: AppConfigService) {
        //EN-TETE HTTP AVEC LES IDENTIFIANTS
        let myHeaders: HttpHeaders = new HttpHeaders();

        //ON APPLIQUE LES IDENTIFIANTS A L'EN-TËtemp
        myHeaders = myHeaders
            .append('Authorization', "Basic " + btoa("jeremy.perrouault:123456"));

        //OPTIONS HTTP POUR LA REQUETE
        this.httpOptions = {
            headers: myHeaders
        };
    }

    findAll() {
        this.httpClient
            .get<Produit[]>("http://localhost:8080/api/produit")
            //.subscribe(resp => this.produits = resp) //VERSION UN TRAITEMENT
            .subscribe(resp => {
                console.log(resp);
                this.produits = resp;
            });
    }

    findAllAsync() {
        if (this.produitsAsync == null) {
            this.produitsAsync = this.httpClient
                .get("http://localhost:8080/api/produit", this.httpOptions);
        }

        return this.produitsAsync;
    }

    refresh() {
        this.produitsAsync = null;
    }




    findByNom(nom: string) {
    }

    findById(id: number) {
        return this.httpClient
            .get("http://localhost:8080/api/produit/" + id, this.httpOptions);
    }

    save(produit: Produit) {
        if (produit.id > 0) { //MODIFICATION
            this.httpClient
                .put("http://localhost:8080/api/produit/" + produit.id, produit, this.httpOptions)
                .subscribe(resp => this.refresh());
            }

        else { //AJOUT
            this.httpClient
                .post("http://localhost:8080/api/produit", produit, this.httpOptions)
                .subscribe(resp => this.refresh());
        }
    }


    delete(produit: Produit) {
        this.httpClient
            .delete("http://localhost:8080/api/produit/" + produit.id, this.httpOptions)
            .subscribe(resp => this.refresh());
    }
}
