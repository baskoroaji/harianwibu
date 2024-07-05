import { Component, Input } from '@angular/core';
import { PostResponse } from 'src/app/services/models';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent {
  private _post: PostResponse = {};
  private _postImage: string | undefined;

  get postImage(): string | undefined{
    if (this._post.image){
      return 'data:image/jpg;base64,' + this._post.image;
    }
    return 'D:\HarianWibu\HarianWibuUI\src\assets\Screenshot 2022-10-31 001510.png';
  }

  get post(): PostResponse{
    return this._post;
  }

  @Input()
  set post(value: PostResponse){
    this._post = value;
}
}
