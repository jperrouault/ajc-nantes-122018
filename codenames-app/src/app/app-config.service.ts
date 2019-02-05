import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class AppConfigService {
    public url: string = "";

    constructor() {
        this.url = "http://localhost:8080/api";
    }
}
