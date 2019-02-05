import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PlateauService } from '../plateau.service';
import { Grille } from '../grille';

@Component({
    templateUrl: './plateau.component.html',
    styleUrls: ['./plateau.component.css']
})
export class PlateauComponent implements OnInit {
    private eventSource: EventSource;

    constructor(private route: ActivatedRoute, private router: Router, private plateauService: PlateauService, private changeDetector: ChangeDetectorRef) {
        this.route.params.subscribe(params => {
            if (params.id == "create") {
                this.plateauService.create().subscribe((resp: Grille) => {
                    this.router.navigate(['/plateau', resp.id]);
                    return;
                });
            }

            else if (params.id == "free") {
                this.plateauService.findFreeOne().subscribe((resp: Grille) => {
                    this.router.navigate(['/plateau', resp.id]);
                    return;
                });
            }

            this.plateauService.findById(params.id, (params.espion == 'espion'));
        });

        this.eventSource = new EventSource(this.plateauService.url + "/subscribe-events");

        let that = this;
        this.eventSource.onmessage = function(resp) {
            let myReponse = JSON.parse(resp.data);

            if (myReponse.terminee == true) {
                alert('PARTIE FINIE !!');
                that.eventSource.onmessage = null;
            }

            else {
                for (let c of that.plateauService.grille.cases) {
                    if (c.id == myReponse.id) {
                        c.couleur = myReponse.couleur;
                        c.revelee = myReponse.revelee;

                        //ON REFRESH (UTILE POUR LES UTILISATEURS QUI N'ONT PAS FAIT L'ACTION)
                        that.changeDetector.detectChanges();
                        return;
                    }
                }
            }
        };
    }

    ngOnInit() { }
}
