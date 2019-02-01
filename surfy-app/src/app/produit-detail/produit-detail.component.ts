import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-produit-detail',
  templateUrl: './produit-detail.component.html',
  styleUrls: ['./produit-detail.component.css']
})
export class ProduitDetailComponent implements OnInit {
    private id: number = 0;

    constructor(private route: ActivatedRoute) {
        this.route.params.subscribe(params => {
            this.id = params.id;
        });

        //SI ON L'AVAIT EU EN PARAMETRE DANS LA REQUETE
        // this.route.queryParams.subscribe(params => {
        //     this.id = params.id;
        // });
    }

    ngOnInit() {
    }
}
