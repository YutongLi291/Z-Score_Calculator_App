package com.example.z_scorecalculatorapp;

import org.junit.Test;

import network.GradesApi;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class GradesApiTest {




    @Test
    public void getAverageTest() {
        GradesApi gradesApi = new GradesApi("MATH", "100", "80");
        assertEquals(0.69, gradesApi.searchCourseThenGetZscore(), 0.5 );
    }
}