import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from 'app/security/authentication.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-friends-profile',
  templateUrl: './friends-profile.component.html',
  styleUrls: ['./friends-profile.component.css']
})
export class FriendsProfileComponent implements OnInit {

  posts: any[];

  addCommentClickedList: any[];

  showCommentsList: any[];

  addMessageClicked: any;

  currentUser : any;

  newMessage: any = {
    text: ''
  };

  newComment: any = {
    text: ''
  };

  id: number;

  private sub: any;

  currentPage = 0;

  numberOfPages: number;

  constructor(private http: HttpClient,private authenticationService: AuthenticationService,
    private route: ActivatedRoute) { 
      this.sub = this.route.params.subscribe(params => {
         this.id = +params['id'];  
         
     });
  }

  ngOnInit() {
    this.posts = [];
    this.addCommentClickedList = [];
    this.showCommentsList = [];

    this.loadPosts();
    this.getCurrentUser();

  }

  getCurrentUser(){
    this.currentUser = this.authenticationService.getCurrentUser();
  }


  loadPosts() {
    this.http.get(`/api/user/friends/${this.id}`).subscribe( data => {
      this.posts = data as any[];

      this.posts.forEach(p => {
        this.addCommentClickedList[p.id] = false;
       });

       this.posts.forEach(p => {
        this.showCommentsList[p.id] = false;
       });
       
       console.log(this.showCommentsList);
       console.log(this.posts);

     });
  }

  showCommentInputForm(id: any){
    this.addCommentClickedList[id] = true;
 }

 showAddMessageForm(id: any){
  if(this.addMessageClicked){
    this.addMessageClicked = false;

  }else{
    this.addMessageClicked = true;
  }
}

 addComment(id: any){
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  this.http.post(`api/post/${id}/add-comment`, JSON.stringify(this.newComment), 
  {headers}).subscribe((data: any) => {
    this.addCommentClickedList[id] = false;
  });
 }

 showComments(id: any){

  console.log(this.showCommentsList[id]);
  if(this.showCommentsList[id]){
  this.showCommentsList[id] = false;
 }else{
   this.showCommentsList[id] = true;
 }
}

checkUsersUsername(username: any){
   return this.currentUser.username === username;
  
 }



}
