
package com.mycompany.probandoapis;

import java.io.InputStream;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

//http://www.oracle.com/technetwork/articles/java/json-1973242.html
public class ProbandoJSON1 {
    public static void main(String[] args) throws Exception{
        URL url = new URL("http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=BMI");
        try ( InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is) ) {
            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("data");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                System.out.print(result.getJsonObject("from").getString("name"));
                System.out.print(": ");
                System.out.println(result.getString("message", ""));
                System.out.println("-----------");
            }
        }
    }
}
