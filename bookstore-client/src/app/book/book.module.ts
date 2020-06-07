import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BookListComponent } from './book-list/book-list.component';
import { BookEditComponent } from './book-edit/book-edit.component';
import { BookService } from './book.service';
import { BOOK_ROUTES } from './book.routes';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(BOOK_ROUTES)
  ],
  declarations: [
    BookListComponent,
    BookEditComponent
  ],
  providers: [BookService],
  exports: []
})
export class BookModule { }
