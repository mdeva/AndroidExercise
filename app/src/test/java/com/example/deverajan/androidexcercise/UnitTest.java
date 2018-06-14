package com.example.deverajan.androidexcercise;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import Constant.Constant;
import utils.Utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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