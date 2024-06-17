import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthRequest } from 'src/app/services/models/auth-request';
import { AuthControllerService } from 'src/app/services/services/auth-controller.service';
import { TokenService } from 'src/app/services/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  authRequest: AuthRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthControllerService,
    private tokenService: TokenService
  ){
}
login() {
  this.errorMsg = []
  this.authService.authenticate({
    body: this.authRequest
  }).subscribe({
    next: (res) => {
    this.tokenService.token = res.token as string;
      this.router.navigate(['post']);
    },
    error: (err) => {
      console.log(err);
      if (err.error.validationErrors) {
        this.errorMsg = err.error.validationErrors;
      } else {
        this.errorMsg.push(err.error.error || err.error.errorMsg);
      }
    }
  });
}
register() {
  this.router.navigate(['register']);
}
}


