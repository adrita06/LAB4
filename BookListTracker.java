import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookListTracker {
    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {

        String filePath = "books.csv"; // or "books.csv" or "books.xml"
        BookReader bookReader;

        // Dynamically select the right reader based on file extension
        if (filePath.endsWith(".csv")) {
            bookReader = new CSVFileReader();
        } else if (filePath.endsWith(".json")) {
            bookReader = new JSONFileReader();
        } else if (filePath.endsWith(".xml")) {
            bookReader = new XMLFileReader();
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }

        // Use the appropriate reader to read the file
        bookReader.readBooks(filePath);
        System.out.println("Total number of books read last year: " + getTotalBooksLastYear(books,2023));
        System.out.println("Books read per month in 2023: " + booksReadPerMonth(books,2023));
        System.out.println("Top 10 longest books:");
        for (Book book : top10LongestBooks()) {
            System.out.println(book.title + " by " + book.author + " (" + book.numberOfPages + " pages)");
        }
    }
    public static int getTotalBooksLastYear(ArrayList<Book> books,int year) {
        int count = 0;
        for (Book book : books) {
            if(book.finishedDate.getYear()==year){
                count++;
            }
        }
        return count;
    }
    public static List<String> booksReadPerMonth(ArrayList<Book> books, int year){
        long[] booksPerMonth = new long[12];
        books.stream().filter(book ->book.finishedDate.getYear()==year).
                forEach(book -> booksPerMonth[book.finishedDate.getMonthValue() - 1]++);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            result.add("Month " + (i + 1) + ": " + booksPerMonth[i] + " books");
        }
        return result;
    }
    public static List<Book> top10LongestBooks() {
        return books.stream()
                .sorted((b1, b2) -> Integer.compare(b2.numberOfPages, b1.numberOfPages))
                .limit(10)
                .collect(Collectors.toList());
    }
}