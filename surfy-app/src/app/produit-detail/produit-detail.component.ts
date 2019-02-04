import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProduitService } from '../produit.service';
import { Produit } from '../produit/produit';

@Component({
  selector: 'app-produit-detail',
  templateUrl: './produit-detail.component.html',
  styleUrls: ['./produit-detail.component.css']
})
export class ProduitDetailComponent implements OnInit {
    private id: number = 0;
    private produit: any;

    constructor(private route: ActivatedRoute, private produitService: ProduitService) {
        this.route.params.subscribe(params => {
            this.produit = this.produitService
                .findById(params.id);
        });

        //SI ON L'AVAIT EU EN PARAMETRE DANS LA REQUETE
        // this.route.queryParams.subscribe(params => {
        //     this.id = params.id;
        // });
    }

    ngOnInit() {
    }
}
