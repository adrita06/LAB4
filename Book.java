import java.time.LocalDate;

public class Book {
    String title;
    String author;
    String genre;
    int numberOfPages;
    LocalDate finishedDate;

    public Book(String title,String author,String genre,int numberOfPages,LocalDate finishedDate) {
        this.title = title;
        this.author=author;
        this.genre=genre;
        this.numberOfPages=numberOfPages;
        this.finishedDate=finishedDate;
    }
}
