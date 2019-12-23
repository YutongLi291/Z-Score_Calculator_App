package network;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CoursesAndCodes {




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<String> getFaculties(){
        BufferedReader br = null;
        Object listofFaculties = null;

        try {


            URL url = new URL("https://ubcgrades.com/api/subjects");
            URLConnection urlc = url.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; "
                    + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
            br = new BufferedReader(new InputStreamReader((urlc.getInputStream()), Charset.forName("UTF-8")));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
                listofFaculties = sb.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONArray facultiesArray = (JSONArray) listofFaculties;
        List<String> facultiesList = new ArrayList<String>();
        for(int i = 0; i < facultiesArray.size(); i++){
            facultiesList.add((String) facultiesArray.get(i));
        }
        return facultiesList;
    }
}
