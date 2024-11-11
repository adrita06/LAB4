import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CSVFileReader implements BookReader{

    public void readBooks(String filepath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while (( line = br . readLine () ) != null ){
                String [] data = line . split (",") ;
                String title=data[0];
                String author=data[1];
                String genre=data[2];
                int pageNumber= Integer.parseInt(data[3]);
                LocalDate date = LocalDate.parse(data[4].trim(),formatter);
                BookListTracker.books.add(new Book(title,author,genre,pageNumber,date));

            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
