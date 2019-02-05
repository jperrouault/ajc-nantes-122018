import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Grille } from './grille';
import { AppConfigService } from './app-config.service';
import { ChatService } from './chat.service';

@Injectable({
    providedIn: 'root'
})
export class PlateauService {
    public url: string = "/plateau";
    public id: number;
    public grille: Grille;
    public cases: Array<any>;

    constructor(private httpClient: HttpClient, private appConfig: AppConfigService, private chatService: ChatService) {
        this.url = this.appConfig.url + this.url;
    }

    public create() {
        return this.httpClient.get(this.url + "/create");
    }

    public findFreeOne() {
        return this.httpClient.get(this.url);
    }

    public findById(id: number, isEspion: boolean) {
        this.id = id;
        this.chatService.findById(id);

        this.httpClient
            .get(this.url + "/" + id + ((isEspion) ? "/espion" : ""))
            .subscribe((resp: Grille) => {
                this.grille = resp;
                this.cases = [];
                let k = 0;

                for (let i = 0; i < (resp.cases.length / 5); i++) {
                    this.cases[i] = [];
                    for (let j = 0; j < 5; j++) {
                        this.cases[i][j] = resp.cases[k++];

                        //Si on a la couleur (couleur révélée)
                        if (this.cases[i][j].conditionalCouleur !== undefined) {
                            this.cases[i][j].couleur = this.cases[i][j].conditionalCouleur;
                        }
                    }
                }
            });
    }

    public select(selectedCase) {
        return this.httpClient.post(this.url + "/" + this.id, selectedCase);
    }
}
