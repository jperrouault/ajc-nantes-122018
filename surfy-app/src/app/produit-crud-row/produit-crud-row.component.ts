import { Component, OnInit, Input, HostListener } from '@angular/core';
import { ProduitComponent } from '../produit/produit.component';
import { Produit } from '../produit/produit';

@Component({
  selector: '[produit-row]',
  templateUrl: './produit-crud-row.component.html',
  styleUrls: ['./produit-crud-row.component.css']
})
export class ProduitCrudRowComponent implements OnInit {
    @Input() private produit: Produit;
    private oldProduit: Produit;

    private isNew: boolean = false;
    private isEditing: boolean = false;
    private isDeleting: boolean = false;

    constructor(private produitComponent: ProduitComponent) { }

    //Méthode exécutée après l'initialisation du composant
    public ngOnInit() {
        if (!this.produit) {
            this.produit = new Produit();
            this.isNew = true;
        }
    }


    //Action au double-clique
    @HostListener('dblclick')
    public onDblClick() {
        //On fait une copie du produit (pour pouvoir revenir en arrière)
        this.oldProduit = JSON.parse(JSON.stringify(this.produit));

        //La vue va s'adapter en mode Edition
        this.isEditing = true;
    }

    //Action à la saisie d'une touche au clavier
    @HostListener('keydown', ['$event'])
    onKeyDown(e) {
        if (e.keyCode == 13) { //Si c'est entrée, on valide
            this.edit();
        }
    }


    /*
     * Edition d'un produit
     */
    public edit() {
        this.isEditing = false;

        if (this.isNew) {
            this.produitComponent.getProduits().push(this.produit);
            this.produit = new Produit();
        }
    }


    /*
     * Annuler l'édition ou l'ajout
     */
    public cancel() {
        if (this.oldProduit) {
            //On remet les données du produit avant les modifications
            this.produit = JSON.parse(JSON.stringify(this.oldProduit));
        }

        this.isEditing = false;
        this.isDeleting = false;
    }



    /*
     * Demander la suppression (active dans la vue la validation de la suppression)
     */
    public askDelete() {
        this.isDeleting = true;
    }


    /*
     * Supprimer un produit
     */
    public delete() {
        this.produitComponent.supprimerProduit(this.produit);
    }
}
