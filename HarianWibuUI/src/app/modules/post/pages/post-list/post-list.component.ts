import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PageResponsePostResponse, PostResponse } from 'src/app/services/models';
import { PostContollerService } from 'src/app/services/services';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent {
  page= 0;
  size= 5;
  postRespone: PageResponsePostResponse = {};

  constructor(
    private router: Router,
    private postService: PostContollerService){}

}
