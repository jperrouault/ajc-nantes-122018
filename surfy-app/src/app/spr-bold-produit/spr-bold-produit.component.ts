import { Component, OnInit, HostListener, Input } from '@angular/core';

@Component({
  selector: '[spr-bold-produit]',
  templateUrl: './spr-bold-produit.component.html',
  styleUrls: ['./spr-bold-produit.component.css']
})
export class SprBoldProduitComponent implements OnInit {
    @Input() prefix: string;
    @Input() nom: string;

    constructor() { }
    ngOnInit() {
    }

    @HostListener('click')
    onClick() {
        alert(this.nom);
    }
}
