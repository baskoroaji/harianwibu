import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostRoutingModule } from './post-routing.module';
import { MainComponent } from './pages/main/main.component';
import { MenuComponent } from './components/menu/menu.component';
import { PostListComponent } from './pages/post-list/post-list.component';
import { ArticleComponent } from './components/article/article.component';
import { CreatePostComponent } from './pages/create-post/create-post.component';
import { PostDetailComponent } from './pages/post-detail/post-detail.component';


@NgModule({
  declarations: [
    MainComponent,
    MenuComponent,
    PostListComponent,
    ArticleComponent,
    CreatePostComponent,
    PostDetailComponent
  ],
  imports: [
    CommonModule,
    PostRoutingModule
  ]
})
export class PostModule { }
