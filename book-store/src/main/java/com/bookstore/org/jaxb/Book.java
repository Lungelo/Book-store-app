package com.bookstore.org.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    private String title;
    private int year;
    private String category;
    private double price;

    @XmlElementWrapper(name="authors")
    @XmlElement(name="author")
    private List<com.bookstore.org.jaxb.Author> authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
   // @XmlAttribute
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<com.bookstore.org.jaxb.Author> authors) {
        this.authors = authors;
    }

    public int getYear() {  return year;  }

    public void setYear(int year) {  this.year = year;   }


}
