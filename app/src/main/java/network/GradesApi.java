package network;


import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

//copied from cpsc210 deliverable 10 webpage
public class GradesApi {

    private static final String appId = "1911afbd";
    private static final String apikey = "9020687e5b60151299027fe80f9c35d1";
    private static final String partOneQuery = "https://ubcgrades.com/api/grades/";
    private static final String partTwoQuery = "?results=0%3A1&cal_min=0&cal_max=50000&fields=nf_calories&appId=";
    private static final String partThreeQuery = "&appKey=";
    String faculty;
    String courseNumber;
    Integer myScore;
    Integer zscore;
    //    static String theURL = partOneQuery + search + partTwoQuery + appId + partThreeQuery + apikey;


    public GradesApi (String search) {
        this.search = search;
    }

    //Modifies: this
    //Effects: gets calories back from a food search of keywords
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void searchFoodThenGetCalories() throws MalformedURLException, IOException, ParseException {

        BufferedReader br = null;


        try {

            theUrl = partOneQuery + search + partTwoQuery + appId + partThreeQuery + apikey;
            URL url = new URL(theUrl);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
            getCalorieCount(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    public  void getCalorieCount(StringBuilder sb) throws org.json.simple.parser.ParseException, JSONException {
        String result = sb.toString();

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(result);
        JSONObject jsonObject = (JSONObject) obj;

        Object hits = jsonParser.parse((jsonObject.get("hits")).toString());
        JSONArray hitsArray = (JSONArray) hits;
        Object hitsObject = hitsArray.get(0);
        JSONObject hitsjsonobject = (JSONObject) hitsObject;

        Object fields = hitsjsonobject.get("fields");
        fields = jsonParser.parse(fields.toString());
        JSONObject fieldsObject = (JSONObject) fields;
        Object calorieCount = fieldsObject.get("nf_calories");
        System.out.println("The calorie count is " + calorieCount);
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }
}

