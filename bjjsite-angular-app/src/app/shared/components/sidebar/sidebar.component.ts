import { CommonModule, ViewportScroller } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit {

  constructor(private router: Router, private scroller: ViewportScroller) { }
  ngOnInit() {
  }

  goToHome() {
    this.scroller.scrollToAnchor("home");
  }

  goToAbout() {
    this.scroller.scrollToAnchor("about");
  }

  goToContact() {
    this.scroller.scrollToAnchor("contact");
  };
}
