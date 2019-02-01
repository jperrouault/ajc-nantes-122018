import { Component, OnInit } from '@angular/core';
import { Produit } from './produit';

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.css']
})
export class ProduitComponent implements OnInit {
    private produit: Produit = new Produit("Le produit", 0);
    private produits: Array<Produit> = new Array<Produit>();
    private editing: boolean = false;

    constructor() {
        this.produits.push(new Produit("GoPRO HERO 6", 499));
        this.produits.push(new Produit("DJI MAVIC PRO", 1499));
    }

    ngOnInit() {
    }

    getProduits() {
        return this.produits;
    }

    ajouterProduit() {
        this.produits.push(this.produit);
        this.produit = new Produit("Le produit", 0);
    }

    editerProduit(produit: Produit) {
        this.produit = produit;
        this.editing = true;
    }

    modifierProduit() {
        this.produit = new Produit("Le produit", 0);
        this.editing = false;
    }

    supprimerProduit(produit: Produit) {
        let index = this.produits.indexOf(produit);
        this.produits.splice(index, 1);
    }
}
