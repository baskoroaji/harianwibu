/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { viewAllPost } from '../fn/post-contoller/view-all-post';
import { ViewAllPost$Params } from '../fn/post-contoller/view-all-post';
import { viewAllPost1 } from '../fn/post-contoller/view-all-post-1';
import { ViewAllPost1$Params } from '../fn/post-contoller/view-all-post-1';

@Injectable({ providedIn: 'root' })
export class PostContollerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `viewAllPost()` */
  static readonly ViewAllPostPath = '/api/posts/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `viewAllPost()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewAllPost$Response(params: ViewAllPost$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return viewAllPost(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `viewAllPost$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewAllPost(params: ViewAllPost$Params, context?: HttpContext): Observable<string> {
    return this.viewAllPost$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `viewAllPost1()` */
  static readonly ViewAllPost1Path = '/api/posts/all';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `viewAllPost1()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewAllPost1$Response(params?: ViewAllPost1$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return viewAllPost1(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `viewAllPost1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewAllPost1(params?: ViewAllPost1$Params, context?: HttpContext): Observable<string> {
    return this.viewAllPost1$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

}
