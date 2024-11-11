import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JSONFileReader implements BookReader {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private JSONParser jsonParser = new JSONParser();

    // Assuming BookListTracker.books is a static list of books
    private List<Book> books = BookListTracker.books;

    public void readBooks(String filePath) {
        try {
            // Parsing the contents of the JSON file
            Object obj = jsonParser.parse(new FileReader(filePath));

            // Cast the parsed object to a JSONArray
            // Assuming JSON structure is an array of book objects
            JSONArray booksArray = (JSONArray) obj;

            // Loop through each book in the array and extract its details
            for (Object objBook : booksArray) {
                JSONObject jsonObject = (JSONObject) objBook;

                // Extract details from jsonObject and create a Book object
                String title = (String) jsonObject.get("title");
                String author = (String) jsonObject.get("author");
                String genre = (String) jsonObject.get("genre");
                int pages = ((Long) jsonObject.get("pages")).intValue(); // Casting Long to int
                LocalDate dateRead = LocalDate.parse((CharSequence) jsonObject.get("date-read"),formatter);

                // Create a new Book object
                Book book = new Book(title, author, genre, pages, dateRead);

                // Add the book to the BookListTracker's collection of books
                books.add(book);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}