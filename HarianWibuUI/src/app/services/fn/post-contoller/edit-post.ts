/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PostRequest } from '../../models/post-request';
import { PostResponse } from '../../models/post-response';

export interface EditPost$Params {
  'post-id': number;
  request: PostRequest;
}

export function editPost(http: HttpClient, rootUrl: string, params: EditPost$Params, context?: HttpContext): Observable<StrictHttpResponse<PostResponse>> {
  const rb = new RequestBuilder(rootUrl, editPost.PATH, 'patch');
  if (params) {
    rb.path('post-id', params['post-id'], {});
    rb.query('request', params.request, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PostResponse>;
    })
  );
}

editPost.PATH = '/api/posts/edit/{post-id}';
