import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfigService } from './app-config.service';

@Injectable({
    providedIn: 'root'
})
export class ChatService {
    public url: string = "/chat";
    public id: number;
    public messages: any;

    constructor(private httpClient: HttpClient, private appConfig: AppConfigService) {
        this.url = this.appConfig.url + this.url;
    }

    public findById(id: number) {
        this.id = id;

        this.httpClient
            .get(this.url + "/" + id)
            .subscribe(resp => this.messages = resp);
    }

    public save(message: string) {
        let myMessage = {
            contenu: message,
            partie: { id: 1 },
            joueur: { id: 1 }
        };

        this.httpClient
            .post(this.url + "/" + this.id, myMessage)
            .subscribe();
    }
}
