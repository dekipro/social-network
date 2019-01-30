import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'app/security/authentication.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-friends',
  templateUrl: './show-friends.component.html',
  styleUrls: ['./show-friends.component.css']
})
export class ShowFriendsComponent implements OnInit {

  friends : any[];
  currentUser : any;
  constructor(private http: HttpClient,private authenticationService:AuthenticationService,
    private router: Router) { }

  ngOnInit() {
    this.loadFriends();
  }

  loadFriends(){
    this.http.get('/api/user/friends').subscribe( data => {
      this.friends = data as any[];
    });
  }

  details = function (id) {
    this.router.navigate(['/friends',id]);
}  
}
