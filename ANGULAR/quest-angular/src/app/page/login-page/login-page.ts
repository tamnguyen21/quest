import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthRequestDto } from '../../dto/auth-request-dto';
import { AuthService } from '../../service/auth-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login-page',
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage implements OnInit {
  protected userForm!: FormGroup;
  protected usernameCtrl!: FormControl;
  protected passwordCtrl!: FormControl;

  constructor(private authService: AuthService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.usernameCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', [ Validators.required, Validators.minLength(6) ]);

    this.userForm = this.formBuilder.group({
      username: this.usernameCtrl,
      password: this.passwordCtrl
    });
  }

  public connecter() {
    this.authService.auth(new AuthRequestDto(this.userForm.value.username, this.userForm.value.password));
  }
}
