import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ChatService } from '../chat.service';

@Component({
    selector: 'chat',
    templateUrl: './chat.component.html',
    styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
    private message: string = "";
    private eventSource: EventSource;

    constructor(private chatService: ChatService, private changeDetector: ChangeDetectorRef) {
        this.eventSource = new EventSource(this.chatService.url + "/subscribe-events");

        let that = this;
        this.eventSource.onmessage = function(resp) {
            let myMessage = JSON.parse(resp.data);
            that.chatService.messages.unshift(myMessage); //INSERE EN PREMIERE POSITION

            //ON REFRESH (UTILE POUR LES UTILISATEURS QUI N'ONT PAS FAIT L'ACTION)
            that.changeDetector.detectChanges();
        };
    }

    ngOnInit() { }


    envoyer() {
        this.chatService.save(this.message);
        this.message = "";
    }
}
