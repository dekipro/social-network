import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MainComponentComponent } from './main-component/main-component.component';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationService } from './security/authentication.service';
import { CanActivateAuthGuard } from './security/can-activate-auth.guard';
import { JwtUtilsService } from './security/jwt-utils.service';
import { TokenInterceptorService } from './security/token-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { ShowAllUsersComponent } from './show-all-users/show-all-users.component';
import { ShowFriendsComponent } from './show-friends/show-friends.component';
import { FriendsProfileComponent } from './friends-profile/friends-profile.component';
import { ChildCommentComponent } from './child-comment/child-comment.component';

const routes: Routes = [
  {path: 'friends/:id', component: FriendsProfileComponent},
  {path: 'friends', component: ShowFriendsComponent},
  {path: 'users', component: ShowAllUsersComponent},
  {path: 'main', component: MainComponentComponent, canActivate: [CanActivateAuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: 'main', pathMatch: 'full'},
  {path: '**', component: PageNotFoundComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    MainComponentComponent,
    LoginComponent,
    ShowAllUsersComponent,
    ShowFriendsComponent,
    FriendsProfileComponent,
    ChildCommentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      routes,
      { enableTracing: true }
    )
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    AuthenticationService,
    CanActivateAuthGuard,
    JwtUtilsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
