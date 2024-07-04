import { Component } from '@angular/core';
import { PostResponse } from 'src/app/services/models';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent {
  private _post: PostResponse = {};

  get post(): PostResponse{
    return this._post;
  }
}
