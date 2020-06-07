import { Routes } from '@angular/router';
import { BookListComponent } from './book-list/book-list.component';
import { BookEditComponent } from './book-edit/book-edit.component';

export const BOOK_ROUTES: Routes = [
  {
    path: 'books',
    component: BookListComponent
  },
  {
    path: 'books/:id',
    component: BookEditComponent
  }
];
