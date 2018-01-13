package com.example.eduardo.survey.filter;


import com.example.eduardo.survey.utility.Store;

/**
 * Factory class to provide abstract creation of OcrFilters from OcrCaptureActivity
 */

public class FilterFactory {

    private FilterFactory() {

    }

    public static OcrFilter getFilter(FilterListener listener, int id) {

        if (id == Store.STORE_WALMART) {
            return new WalmartOcrFilter(listener);
        }

        return null;
    }

}
