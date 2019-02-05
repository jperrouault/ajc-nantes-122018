import { Component, OnInit, HostListener, Input } from '@angular/core';
import { PlateauService } from '../plateau.service';

@Component({
    selector: 'case',
    templateUrl: './case.component.html',
    styleUrls: ['./case.component.css']
})
export class CaseComponent implements OnInit {
    @Input() private case: any;

    constructor(private plateauService: PlateauService) { }

    ngOnInit() { }

    @HostListener('click')
    onClick() {
        if (!this.case.couleur) { //SI LA CARTE N'A PAS DE COULEUR (ESPION OU REVELEE)
            this.plateauService
                .select(this.case)
                .subscribe();
        }
    }
}
