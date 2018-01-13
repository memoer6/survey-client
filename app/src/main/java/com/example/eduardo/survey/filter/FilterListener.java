package com.example.eduardo.survey.filter;

import android.os.Bundle;

/**
 * Callback to be implemented by OcrCaptureActivity where concrete OcrFilters send the captured text
 */

public interface FilterListener {

    void onCaptureAllText(Bundle args);

}
