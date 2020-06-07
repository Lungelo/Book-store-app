package com.bookstore.org;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.bookstore.org.model.Author;
import com.bookstore.org.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookstoreApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllBooks() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/books",
				HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetBookById() {
		Book book = restTemplate.getForObject(getRootUrl() + "/api/v1/books/1", Book.class);
		System.out.println(book.getCategory());
		System.out.println(book.getTitle());
		System.out.println(book.getYear());
		System.out.println(book.getPrice());
		assertNotNull(book);
	}

	@Test
	public void testGetAuthorById() {
		Author author = restTemplate.getForObject(getRootUrl() + "/api/v1/authors/1", Author.class);
		System.out.println(author.getName());
		assertNotNull(author);
	}

	@Test
	public void testCreateBook() {
		Book book  = new Book();
		Author firstAuthor = new Author();
		Author secondAuthor = new Author();
		Set<Author>  authors = new HashSet<Author>();

		firstAuthor.setId(1);
		firstAuthor.setName("Lungelo H Qwabe");

		secondAuthor.setId(2);
		secondAuthor.setName("Zandi T Khumalo");

		authors.add(firstAuthor);
		authors.add(secondAuthor);

		book.setId(1);
		book.setCategory("web");
		book.setTitle("J2EE programming");
		book.setYear(2000);
		book.setAuthors(authors);
		book.setPrice(109.00);
		ResponseEntity<Book> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/books", book, Book.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testCreateAuthor() {
		Author author = new Author();
		author.setId(1);
		author.setName("Lungelo H Qwabe");
		ResponseEntity<Author> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/authors", author, Author.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateBook() {
		int id = 1;
		Book  book = restTemplate.getForObject(getRootUrl() + "/api/v1/books/" + id, Book.class);
		book.setTitle("J2EE programming 2nd Edition");
		book.setYear(2001);
		book.setPrice(250.00);
		restTemplate.put(getRootUrl() + "/api/v1/books/" + id, book);
		Book updatedBook = restTemplate.getForObject(getRootUrl() + "/api/v1/books/" + id, Book.class);
		assertNotNull(updatedBook);
	}

	@Test
	public void testUpdateAuthor() {
		int id = 1;
		Author  author = restTemplate.getForObject(getRootUrl() + "/api/v1/authors/" + id, Author.class);
		author.setName("Lungelo S Qwabe");
		restTemplate.put(getRootUrl() + "/api/v1/books/" + id, author);
		Author updatedAuthor = restTemplate.getForObject(getRootUrl() + "/api/v1/authors/" + id, Author.class);
		assertNotNull(updatedAuthor);
	}

	@Test
	public void testDeleteBook() {
		int id = 2;
		Book book = restTemplate.getForObject(getRootUrl() + "/api/v1/books/" + id, Book.class);
		assertNotNull(book);
		restTemplate.delete(getRootUrl() + "/api/v1/books/" + id);
		try {
			book = restTemplate.getForObject(getRootUrl() + "/api/v1/books/" + id, Book.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Test
	public void testDeleteAuthor() {
		int id = 2;
		Author author = restTemplate.getForObject(getRootUrl() + "/api/v1/authors/" + id, Author.class);
		assertNotNull(author);
		restTemplate.delete(getRootUrl() + "/api/v1/authors/" + id);
		try {
			author = restTemplate.getForObject(getRootUrl() + "/api/v1/authors/" + id, Author.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}


}
