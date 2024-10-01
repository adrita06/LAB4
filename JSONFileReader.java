import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONFileReader<JSONObject> {
    JSONParser jsonParser = new JSONParser();

    {
        //Parsing the contents of the JSON file
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Users\\Youtech BD\\OneDrive\\Desktop\\BOOKS.JSON"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
