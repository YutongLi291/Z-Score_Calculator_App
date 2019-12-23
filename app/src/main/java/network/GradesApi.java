package network;


import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

;


//copied from cpsc210 deliverable 10 webpage
public class GradesApi {

    private static final String partOneQuery = "https://ubcgrades.com/api/grades/2018W/";
    String theUrl;

    String faculty;  //usually 4 capital letters
    String courseNumber; //3 digits
    String myScore; //a two digit percentage
    Double zscore;
    double myScoreDouble;



    public GradesApi (String faculty, String courseNumber, String myScore ) {
        this.faculty = faculty.toUpperCase();
        this.courseNumber = courseNumber;
        this.myScore = myScore;
        myScoreDouble = Double.parseDouble(myScore);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    //Modifies: this
    //Effects: gets calories back from a food search of keywords
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public double searchCourseThenGetZscore() throws IOException, ParseException {

        BufferedReader br = null;




            theUrl = partOneQuery + faculty + "/" + courseNumber;
            URL url = null;

                url = new URL(theUrl);

            URLConnection urlc = null;

                urlc = url.openConnection();

            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; "
                    + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

                br = new BufferedReader(new InputStreamReader((urlc.getInputStream())
                        , Charset.forName("UTF-8")
                ));

            String line = null;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {



                sb.append(line);
                sb.append(System.lineSeparator());
            }
        if (br != null) {
            br.close();

        }
            return getzScore(sb);




    }



    public  double getzScore(StringBuilder sb) throws org.json.simple.parser.ParseException{
        String result = sb.toString();

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(result);
//        JSONObject jsonObject = (JSONObject) obj;

        Object all = jsonParser.parse(obj.toString());
        org.json.simple.JSONArray allArray = (org.json.simple.JSONArray) all;
        Object gradesObject = allArray.get(allArray.size() - 1);
        JSONObject gradeJsonObject = (JSONObject) gradesObject;

        Object stats = gradeJsonObject.get("stats");
        stats = jsonParser.parse(stats.toString());
        JSONObject statsObject = (JSONObject) stats;
        Object classAverage = statsObject.get("average");
        System.out.println("The course average is  " + classAverage);
        Object standardDeviation = statsObject.get("stdev");
        System.out.println("The course standard deviation is " + standardDeviation);
        zscore = (myScoreDouble - ((double) classAverage)) / ((double)standardDeviation);
        return zscore;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }
}

