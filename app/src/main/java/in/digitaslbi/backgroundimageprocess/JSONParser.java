package in.digitaslbi.backgroundimageprocess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * Created by  on 06-09-2015.
 */
public class JSONParser {

    // give the parsed result of movie list
    public static List<Item> parseMovieList(String jSonString) {
        List<Item> items = new ArrayList();

        try {
            JSONObject jsonObject = new JSONObject(jSonString);
            jsonObject = jsonObject.getJSONObject("Animals");

            JSONArray jsonArray = jsonObject.getJSONArray("favourites");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject origArray = jsonArray.getJSONObject(i);

                String title = origArray.getString("species");
                String url = origArray.getString("picture");

                Item temp = new Item(title, url);
                items.add(temp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    /* The date/time conversion code is going to be moved outside the asynctask later,
            * so for convenience we're breaking it out into its own method now.
            */
    private static String getReadableDateString(long time) {
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
        return shortenedDateFormat.format(time);
    }

}
