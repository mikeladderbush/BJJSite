import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  user: any;

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.getUser(1);
  }

  getUser(id: number): void {
    this.http.get(`http://localhost:8080/api/users/${id}`)
      .subscribe({
        next: data => {
          this.user = data;
          console.log(this.user);
          this.cdr.detectChanges();
        },
        error: error => {
          console.error('There was an issue getting a user', error);
        },
        complete: () => {
          console.log('Request complete');
        }
      });
  }
}
