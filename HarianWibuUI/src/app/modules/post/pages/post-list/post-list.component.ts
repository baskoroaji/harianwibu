import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PageResponsePostResponse, PostResponse } from 'src/app/services/models';
import { PostContollerService } from 'src/app/services/services';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {
  postResponse: PageResponsePostResponse = {};
  page= 0;
  size= 5;
  pages: any = [];
  

  constructor(
    private router: Router,
    private postService: PostContollerService){}

  ngOnInit(): void {
    this.findAllPost();
  }
  private findAllPost() {
    this.postService.getAllPosts({page: this.page, size: this.size})
    .subscribe({next: (post) => {
      this.postResponse = post; 
      this.pages = Array(this.postResponse.totalPages)
      .fill(0).map((x,i)=> i);
    }
      
    })
  }
  gotToPage(page: number) {
    this.page = page;
    this.findAllPost();
  }
  goToNextPage(){
    this.page++;
    this.findAllPost();
  }
  goToPreviousPage(){
    this.page--;
    this.findAllPost();
  }
  get isLastPage(){
    return this.page === this.postResponse.totalPages as number -1;
  }
}
