import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;

public class XMLFileReader implements BookReader {

    public void readBooks(String filePath) {
        try {
            // Load the XML document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);

            // Normalize the XML structure
            document.getDocumentElement().normalize();

            // Get all the book elements
            NodeList bookNodes = document.getElementsByTagName("book");

            for (int i = 0; i < bookNodes.getLength(); i++) {
                Element bookElement = (Element) bookNodes.item(i);

                String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
                String genre = bookElement.getElementsByTagName("genre").item(0).getTextContent();
                int pages = Integer.parseInt(bookElement.getElementsByTagName("pages").item(0).getTextContent());

                // Convert date_read string to LocalDate (assuming format "yyyy-MM-dd")
                String dateReadString = bookElement.getElementsByTagName("date_read").item(0).getTextContent();
                LocalDate dateRead = LocalDate.parse(dateReadString); // No need for custom formatter

                BookListTracker.books.add(new Book(title,author,genre,pages,dateRead));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
