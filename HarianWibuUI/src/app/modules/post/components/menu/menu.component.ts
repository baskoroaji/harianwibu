import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/services/authentication.service';
import { TokenService } from 'src/app/services/token/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  isAuthenticated = false;
  userName: string[] = [];
  constructor (
    private router: Router,
    private token: TokenService) {}
  ngOnInit(){
      const linkColor = document.querySelectorAll('.nav-link');
      linkColor.forEach(link => {
        if (window.location.href.endsWith(link.getAttribute('href') || '')) {
          link.classList.add('active');
        }
        link.addEventListener('click', () => {
          linkColor.forEach(l => l.classList.remove('active'));
          link.classList.add('active');
        });
      });
      this.isAuthenticated = this.token.isTokenValid();
      this.userName = this.token.userName;
}
  
    onSelect(){
      this.router.navigate(['posts']);
  }

}
