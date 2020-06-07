import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { AuthorService } from '../author.service';
import { Author } from '../author';
import { map, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-author-edit',
  templateUrl: './author-edit.component.html'
})
export class AuthorEditComponent implements OnInit {

  id: string;
  author: Author;
  feedback: any = {};

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authorService: AuthorService) {
  }

  ngOnInit() {
    this
      .route
      .params
      .pipe(
        map(p => p.id),
        switchMap(id => {
          if (id === 'new') { return of(new Author()); }
          return this.authorService.findById(id);
        })
      )
      .subscribe(author => {
          this.author = author;
          this.feedback = {};
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      );
  }

  save() {
    this.authorService.save(this.author).subscribe(
      author => {
        this.author = author;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(() => {
          this.router.navigate(['/authors']);
        }, 1000);
      },
      err => {
        this.feedback = {type: 'warning', message: 'Error saving'};
      }
    );
  }

  cancel() {
    this.router.navigate(['/authors']);
  }
}
