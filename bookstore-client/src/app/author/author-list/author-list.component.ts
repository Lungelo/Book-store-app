import { Component, OnInit } from '@angular/core';
import { AuthorFilter } from '../author-filter';
import { AuthorService } from '../author.service';
import { Author } from '../author';

@Component({
  selector: 'app-author',
  templateUrl: 'author-list.component.html'
})
export class AuthorListComponent implements OnInit {

  filter = new AuthorFilter();
  selectedAuthor: Author;
  feedback: any = {};

  get authorList(): Author[] {
    return this.authorService.authorList;
  }

  constructor(private authorService: AuthorService) {
  }

  ngOnInit() {
    this.search();
  }

  search(): void {
    this.authorService.load(this.filter);
  }

  select(selected: Author): void {
    this.selectedAuthor = selected;
  }

  delete(author: Author): void {
    if (confirm('Are you sure?')) {
      this.authorService.delete(author).subscribe(() => {
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
