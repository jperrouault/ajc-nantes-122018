import { Component, OnInit } from '@angular/core';
import { Produit } from './produit';
import { ProduitService } from '../produit.service';

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.css']
})
export class ProduitComponent implements OnInit {
    private produit: Produit = new Produit("Le produit", 0);
    private produits: Array<Produit>;
    private editing: boolean = false;

    constructor(private produitService: ProduitService) {
        // this.produits = produitService.produits;
        //
        // this.produits.push(new Produit("GoPRO HERO 6", 499));
        // this.produits.push(new Produit("DJI MAVIC PRO", 1499));
    }

    ngOnInit() {
    }

    getProduits() {
        return this.produits;
    }

    ajouterProduit() {
        this.produit.fournisseur = { id: 1 };
        this.produitService.save(this.produit);
    }

    editerProduit(produit: Produit) {
        this.produit = produit;
        this.editing = true;
    }

    modifierProduit() {
        this.produit.fournisseur = { id: 1 };
        this.produitService.save(this.produit);
        
        this.produit = new Produit("Le produit", 0);
        this.editing = false;
    }

    supprimerProduit(produit: Produit) {
        this.produitService.delete(produit);
    }
}
