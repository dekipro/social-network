import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-child-comment',
  templateUrl: './child-comment.component.html',
  styleUrls: ['./child-comment.component.css']
})
export class ChildCommentComponent implements OnInit {

  @Input()
  comment: any;
  
  constructor() { }

  ngOnInit() {
  }

}
