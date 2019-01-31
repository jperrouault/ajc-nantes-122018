import { Component, OnInit, HostListener, Input } from '@angular/core';

@Component({
  selector: 'spr-tooltip,[spr-tooltip]',
  templateUrl: './spr-tooltip.component.html',
  styleUrls: ['./spr-tooltip.component.css']
})
export class SprTooltipComponent implements OnInit {
    @Input('spr-tooltip-titre') titre: string;
    private hover: boolean = false;

    constructor() { }
    ngOnInit() { }

    @HostListener('mouseenter')
    onMouseEnter() {
        this.hover = true;
    }

    @HostListener('mouseleave')
    onMouseLeave() {
        this.hover = false;
    }
}
