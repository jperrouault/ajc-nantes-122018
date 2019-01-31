import { Component, OnInit, HostListener } from '@angular/core';

@Component({
  selector: 'spr-bold',
  templateUrl: './spr-bold.component.html',
  styleUrls: ['./spr-bold.component.css']
})
export class SprBoldComponent implements OnInit {
  constructor() { }

  @HostListener('click')
  onClick() {
      alert("J'ai cliqué ici !");
  }

  ngOnInit() {
  }
}
