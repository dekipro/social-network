import { Component, OnInit } from '@angular/core';
import { Response, RequestOptions,
         Headers } from '@angular/http';

import * as _ from 'lodash';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AuthenticationService } from 'app/security/authentication.service';

@Component({
  selector: 'app-main-component',
  templateUrl: './main-component.component.html',
  styleUrls: ['./main-component.component.css']
})
export class MainComponentComponent implements OnInit {
  

  posts: any[];

  sizeToAddToPosts : any;

  postsToPush : any[];

  commentsForPost: any[];

  newPost: any = {
    tekst: ''
  };
  
  newComment: any = {
    text: ''
  };
  addPostClicked:boolean;

  addCommentClicked: boolean;

  showRequests: any;

  addCommentClickedList: any[];

  showCommentsList: any[];

  recievedFriendRequestList: any[];

  currentUser : any;

  currentPage = 0;

  numberOfPages: number;
   
  ngOnInit(): void {
    this.posts = [];
    this.postsToPush = [];
    this.addCommentClickedList = [];
    this.showCommentsList = [];
    this.recievedFriendRequestList = [];
    this.commentsForPost = [];
    this.loadFriendRequests();
    this.loadPosts();
 
    this.getCurrentUser();
  }


  constructor(private http: HttpClient,private authenticationService: AuthenticationService) {
    this.addPostClicked = false;
    this.addCommentClicked = false;
    this.sizeToAddToPosts = 0;
  }

  loadPosts() {
    const params = new HttpParams()
    .set('page', this.currentPage.toString())
    .set('size', '5');  
    this.http.get('/api/user/posts',{params}).subscribe( data => {
      this.postsToPush = data as any[];
      
      // for (let p of this.postsToPush) {
      //   console.log(p);   
      //  }
      

      this.postsToPush.forEach(p => {
       this.posts.push(p);
       });

       this.postsToPush.forEach(p => {
        this.addCommentClickedList[p.id] = false;
       });

       this.postsToPush.forEach(p => {
        this.showCommentsList[p.id] = false;
       });

        console.log(this.postsToPush);

      this.reset();
    });

  }

  loadFriendRequests(){
    this.http.get('/api/user/recievedFriendRequests').subscribe( data => {
      this.recievedFriendRequestList = data as any[];
      console.log(this.recievedFriendRequestList);

    });
  }

  loadCommentsForPost(p: any){
    this.http.get(`/api/user/posts/${p.id}/comments`).subscribe( data => {

       this.commentsForPost = data as any[];
       (p.commentsDTO)[p.commentsDTO.length] = this.commentsForPost[this.commentsForPost.length-1];
       //moze ovde i push da se upotrebi
      //  console.log(this.commentsForPost);

    });
  }

  changeSizeOfAddToPosts(){
    console.log('radi');
    this.sizeToAddToPosts += 2;
    this.loadPosts();
  }

  hasRole(role: string): boolean {
    return this.authenticationService.hasRole(role);
  }

  showAddForm(id: any){
    if(this.addPostClicked){
      this.addPostClicked = false;

    }else{
      this.addPostClicked = true;
    }
  }

  showCommentInputForm(id: any){
     if(this.addCommentClickedList[id]){
      this.addCommentClickedList[id] = false;

    }else{
      this.addCommentClickedList[id] = true;
    }
  }

  showFriendRequests(){
    if(this.showRequests){
      this.showRequests = false;

    }else{
      this.showRequests = true;
    }
  }

  getCurrentUser(){
    this.currentUser = this.authenticationService.getCurrentUser();
    console.log(this.currentUser.username);
  }

  addPost(){
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    this.http.post('api/user/add-post', JSON.stringify(this.newPost), 
    {headers}).subscribe((data: any) => {
      this.loadPosts();
    });
    this.addPostClicked = false;
   }

   addComment(p: any){
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    this.http.post(`api/post/${p.id}/add-comment`, JSON.stringify(this.newComment), 
    {headers}).subscribe((data: any) => {
      this.addCommentClickedList[p.id] = false;
      this.loadCommentsForPost(p);
      //  (p.commentsDTO)[p.commentsDTO.length] = this.commentsForPost[this.commentsForPost.length-1];
      //  console.log(this.commentsForPost);
    });
   }

   addFriend(user:any){
     
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    this.http.put(`/api/user/addfriend`,JSON.stringify(user),{headers}).subscribe((data: any) => {
      this.loadFriendRequests();
       console.log('dobro je');
    });
  }

   showComments(id: any){
     if(this.showCommentsList[id]){
     this.showCommentsList[id] = false;
    }else{
      this.showCommentsList[id] = true;
    }
   }

   showMessages(){
    this.http.get('/api/user/1/messages').subscribe( data => {

      console.log("radi i ovo");

    });
   }

   checkUsersUsername(username: any){
      return this.currentUser.username === username;
  }
  
   
  reset(){
    this.newPost = {
      datum: '',
      tekst: ''
    };
  }
}
