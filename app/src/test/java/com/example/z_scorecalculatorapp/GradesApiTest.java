package com.example.z_scorecalculatorapp;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import network.GradesApi;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class GradesApiTest {




    @Test
    public void getAverageTest() {
        GradesApi gradesApi = new GradesApi("MATH", "100", "80");
        try {
            assertEquals(69.64, gradesApi.searchCourseThenGetAverage(), 0.5 );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}