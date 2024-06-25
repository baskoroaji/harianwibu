/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { editPost } from '../fn/post-contoller/edit-post';
import { EditPost$Params } from '../fn/post-contoller/edit-post';
import { getAllPosts } from '../fn/post-contoller/get-all-posts';
import { GetAllPosts$Params } from '../fn/post-contoller/get-all-posts';
import { PageResponsePostResponse } from '../models/page-response-post-response';
import { PostResponse } from '../models/post-response';
import { savePost } from '../fn/post-contoller/save-post';
import { SavePost$Params } from '../fn/post-contoller/save-post';
import { uploadImage } from '../fn/post-contoller/upload-image';
import { UploadImage$Params } from '../fn/post-contoller/upload-image';
import { viewPostById } from '../fn/post-contoller/view-post-by-id';
import { ViewPostById$Params } from '../fn/post-contoller/view-post-by-id';

@Injectable({ providedIn: 'root' })
export class PostContollerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getAllPosts()` */
  static readonly GetAllPostsPath = '/api/posts';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllPosts()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllPosts$Response(params?: GetAllPosts$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponsePostResponse>> {
    return getAllPosts(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllPosts$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllPosts(params?: GetAllPosts$Params, context?: HttpContext): Observable<PageResponsePostResponse> {
    return this.getAllPosts$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponsePostResponse>): PageResponsePostResponse => r.body)
    );
  }

  /** Path part for operation `savePost()` */
  static readonly SavePostPath = '/api/posts';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `savePost()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  savePost$Response(params: SavePost$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return savePost(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `savePost$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  savePost(params: SavePost$Params, context?: HttpContext): Observable<number> {
    return this.savePost$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `uploadImage()` */
  static readonly UploadImagePath = '/api/posts/cover/{post-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadImage()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadImage$Response(params: UploadImage$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return uploadImage(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadImage$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadImage(params: UploadImage$Params, context?: HttpContext): Observable<{
}> {
    return this.uploadImage$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `editPost()` */
  static readonly EditPostPath = '/api/posts/edit/{post-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `editPost()` instead.
   *
   * This method doesn't expect any request body.
   */
  editPost$Response(params: EditPost$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return editPost(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `editPost$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  editPost(params: EditPost$Params, context?: HttpContext): Observable<number> {
    return this.editPost$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `viewPostById()` */
  static readonly ViewPostByIdPath = '/api/posts/{post-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `viewPostById()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewPostById$Response(params: ViewPostById$Params, context?: HttpContext): Observable<StrictHttpResponse<PostResponse>> {
    return viewPostById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `viewPostById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewPostById(params: ViewPostById$Params, context?: HttpContext): Observable<PostResponse> {
    return this.viewPostById$Response(params, context).pipe(
      map((r: StrictHttpResponse<PostResponse>): PostResponse => r.body)
    );
  }

}
