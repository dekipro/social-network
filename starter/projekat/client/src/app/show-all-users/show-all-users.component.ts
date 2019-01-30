import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'app/security/authentication.service';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { load } from '@angular/core/src/render3';

@Component({
  selector: 'app-show-all-users',
  templateUrl: './show-all-users.component.html',
  styleUrls: ['./show-all-users.component.css']
})
export class ShowAllUsersComponent implements OnInit {

  users: any[];

  friends: any[];

  friendRequestList: any[];

  friendToAdd : any;

  currentUser : any;
  constructor(private http: HttpClient,private authenticationService: AuthenticationService) {
     this.friendRequestList = [];
     this.friends = [];
   }

  
  ngOnInit() {
    this.loadUsers();
    this.loadFriends();
     this.getCurrentUser();
     this.loadFriendRequests();
  }

  loadUsers() {
    console.log('radi');
    this.http.get('/api/user').subscribe( data => {
      this.users = data as any[];
      
    });
    
  }

  loadFriends(){
    this.http.get('/api/user/friends').subscribe( data => {
      this.friends = data as any[];
    });
  }

  loadFriendRequests(){
    this.http.get('/api/user/sentFriendRequests').subscribe( data => {
      this.friendRequestList = data as any[];
      console.log(this.friendRequestList);

    });
  }
 
 

  sendFriendRequest(user: any){
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
 
    this.http.put(`/api/user/sendFriendRequest`,JSON.stringify(user),{headers}).subscribe((data: any) => {
      this.loadFriendRequests();

      console.log('dobro je');
    });

  }

  getCurrentUser(){
    this.currentUser = this.authenticationService.getCurrentUser();
    console.log(this.currentUser.username);
  }

  
 
  isCurrentUser(username: any){
    return this.currentUser.username === username;  
  }

  isFriend(user: any){
   let friendsUsernames =  this.friends.map(f => f.loginDto.username );
  
   return friendsUsernames.indexOf(user.loginDto.username) != -1;
  }

  isFriendRequestSent(user: any){
    let sentRequests =  this.friendRequestList.map(f => f.id );
  
     return sentRequests.indexOf(user.id) != -1;
  }

}
