package com.aapkatrade.shopping.general;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by abcd on 10/12/2016.
 */
public class Tabletsize {

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }



}
