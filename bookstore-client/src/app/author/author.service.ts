import { Author } from './author';
import { AuthorFilter } from './author-filter';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

const headers = new HttpHeaders().set('Accept', 'application/json');

@Injectable()
export class AuthorService {
  authorList: Author[] = [];
  api = 'http://localhost:8080/api/v1/authors';

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Author> {
    const url = `${this.api}/${id}`;
    const params = { id: id };
    return this.http.get<Author>(url, {params, headers});
  }

  load(filter: AuthorFilter): void {
    this.find(filter).subscribe(result => {
        this.authorList = result;
      },
      err => {
        console.error('error loading', err);
      }
    );
  }

  find(filter: AuthorFilter): Observable<Author[]> {
    const params = {
      'name': filter.name,
    };

    return this.http.get<Author[]>(this.api, {params, headers});
  }

  save(entity: Author): Observable<Author> {
    let params = new HttpParams();
    let url = '';
    if (entity.id) {
      url = `${this.api}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.put<Author>(url, entity, {headers, params});
    } else {
      url = `${this.api}`;
      return this.http.post<Author>(url, entity, {headers, params});
    }
  }

  delete(entity: Author): Observable<Author> {
    let params = new HttpParams();
    let url = '';
    if (entity.id) {
      url = `${this.api}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.delete<Author>(url, {headers, params});
    }
    return null;
  }
}

