import { Book } from './book';
import { BookFilter } from './book-filter';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

const headers = new HttpHeaders().set('Accept', 'application/json');

@Injectable()
export class BookService {
  bookList: Book[] = [];
  api = 'http://localhost:8080/api/v1/books';

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Book> {
    const url = `${this.api}/${id}`;
    const params = { id: id };
    return this.http.get<Book>(url, {params, headers});
  }

  load(filter: BookFilter): void {
    this.find(filter).subscribe(result => {
        this.bookList = result;
      },
      err => {
        console.error('error loading', err);
      }
    );
  }

  find(filter: BookFilter): Observable<Book[]> {
    const params = {
      'title': filter.title,
      'category': filter.category,
    };

    return this.http.get<Book[]>(this.api, {params, headers});
  }

  save(entity: Book): Observable<Book> {
    let params = new HttpParams();
    let url = '';
    if (entity.id) {
      url = `${this.api}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.put<Book>(url, entity, {headers, params});
    } else {
      url = `${this.api}`;
      return this.http.post<Book>(url, entity, {headers, params});
    }
  }

  delete(entity: Book): Observable<Book> {
    let params = new HttpParams();
    let url = '';
    if (entity.id) {
      url = `${this.api}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.delete<Book>(url, {headers, params});
    }
    return null;
  }
}

