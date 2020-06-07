import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthorListComponent } from './author-list/author-list.component';
import { AuthorEditComponent } from './author-edit/author-edit.component';
import { AuthorService } from './author.service';
import { AUTHOR_ROUTES } from './author.routes';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(AUTHOR_ROUTES)
  ],
  declarations: [
    AuthorListComponent,
    AuthorEditComponent
  ],
  providers: [AuthorService],
  exports: []
})
export class AuthorModule { }
