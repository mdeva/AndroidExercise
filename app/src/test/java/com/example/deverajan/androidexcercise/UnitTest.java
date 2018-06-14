package com.example.deverajan.androidexcercise;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import Constant.Constant;
import model.ListModel;
import utils.Utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class UnitTest {

    @Mock
    Context mMockContext;


    @Test
    public void restAPITest()
            throws ClientProtocolException, IOException {
        // Given
        HttpUriRequest request = new HttpGet( "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json");
        // When
        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void restJSONTest()
            throws ClientProtocolException, IOException {

        // Given
        HttpUriRequest request = new HttpGet( "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json" );

        // When
        CloseableHttpResponse response = HttpClientBuilder.create().build().execute( request );

        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");

        // Then
        Gson gson = new GsonBuilder().create();
        ListModel listModel = gson.fromJson(responseString, ListModel.class);

        // Then
        assertEquals("About Canada",listModel.getTitle() );
    }



    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void initWithContext() throws Exception {
        assertNotNull(mMockContext); // PASS
    }

    @Test
    public void getDeviceDensityTest() {
        final int densityLow = 120;
        final int densityMedium = 160;
        final int densityHigh = 240;
        final int densityXHigh = 320;
        final int upDensity = 800;

        final Resources mockResources = mock(Resources.class);
        final DisplayMetrics mockDisplayMetrics = mock(DisplayMetrics.class);
        when(mockResources.getDisplayMetrics()).thenReturn(mockDisplayMetrics);

        // low test
        densityTest(densityLow, mockResources, mockDisplayMetrics, Constant.DENSITY_LDPI);

        // medium test
        densityTest(densityMedium, mockResources, mockDisplayMetrics, Constant.DENSITY_MDPI);

        // high test
        densityTest(densityHigh, mockResources, mockDisplayMetrics, Constant.DENSITY_HDPI);

        // xhigh test
        densityTest(densityXHigh, mockResources, mockDisplayMetrics, Constant.DENSITY_XHDPI);

        // greater test
        densityTest(upDensity, mockResources, mockDisplayMetrics, Constant.DENSITY_XXHDPI);
    }

    private void densityTest(int density, Resources mockResources, DisplayMetrics mockDisplayMetrics, String expectedValue) {
        mockDisplayMetrics.densityDpi = density;
        String result = Utils.getDeviceDensity(mockResources.getDisplayMetrics());
        assertThat(result, is(expectedValue));
    }
}