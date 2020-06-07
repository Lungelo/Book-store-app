package com.bookstore.org.util;

import com.bookstore.org.jaxb.Author;
import com.bookstore.org.jaxb.Book;
import com.bookstore.org.jaxb.Bookstore;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookUtil {

public static void main(String[] args) throws JAXBException, FileNotFoundException {

    BookUtil.convertXML();
}
    private static void convertXML() throws JAXBException, FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:results.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Bookstore.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Bookstore books = (Bookstore) jaxbUnmarshaller.unmarshal( file );

        for(Book book : books.getBooks())
        {
            System.out.println(book.getTitle());
            System.out.println(book.getPrice());
        }
    }

}
