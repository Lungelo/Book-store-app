import { Component, OnInit } from '@angular/core';
import { BookFilter } from '../book-filter';
import { BookService } from '../book.service';
import { Book } from '../book';

@Component({
  selector: 'app-book',
  templateUrl: 'book-list.component.html'
})
export class BookListComponent implements OnInit {

  filter = new BookFilter();
  selectedBook: Book;
  feedback: any = {};

  get bookList(): Book[] {
    return this.bookService.bookList;
  }

  constructor(private bookService: BookService) {
  }

  ngOnInit() {
    this.search();
  }

  search(): void {
    this.bookService.load(this.filter);
  }

  select(selected: Book): void {
    this.selectedBook = selected;
  }

  delete(book: Book): void {
    if (confirm('Are you sure?')) {
      this.bookService.delete(book).subscribe(() => {
          this.feedback = {type: 'success', message: 'Delete was successful!'};
          setTimeout(() => {
            this.search();
          }, 1000);
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error deleting.'};
        }
      );
    }
  }
}
