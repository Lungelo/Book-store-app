import { Routes } from '@angular/router';
import { AuthorListComponent } from './author-list/author-list.component';
import { AuthorEditComponent } from './author-edit/author-edit.component';

export const AUTHOR_ROUTES: Routes = [
  {
    path: 'authors',
    component: AuthorListComponent
  },
  {
    path: 'authors/:id',
    component: AuthorEditComponent
  }
];
