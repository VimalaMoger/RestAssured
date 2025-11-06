package excelDrivenTest;

import com.moger.automation.pojos.Author;
import com.moger.automation.pojos.Book;
import com.moger.automation.pojos.Publisher;
import org.json.JSONException;
import java.util.Arrays;
import java.util.List;

public class GetBook {

    static Book getBook (List<String> data) throws JSONException {

        int index = 0;

        Book newBook = new Book();
        newBook.setId(Long.parseLong(data.get(index++)));
        newBook.setTitle(data.get(index++));
        newBook.setImage_url(data.get(index++));
        newBook.setPublicationYear(Integer.parseInt(data.get(index++)));

        List<String> authors = Arrays.asList(data.get(index++));
        Author author = new Author();
        author.setName(authors.get(0));

        newBook.setAuthors(Arrays.asList(author));

        List<String> publishers = Arrays.asList(data.get(index++));
        Publisher publisher = new Publisher();
        publisher.setName(publishers.get(0));

        newBook.setPublishers(Arrays.asList(publisher));

        newBook.setGenre(Book.BookGenre.values()[Integer.parseInt(data.get(index++))]);
        newBook.setAmazonRating(Double.parseDouble(data.get(index++)));

        return newBook;
    }
}
