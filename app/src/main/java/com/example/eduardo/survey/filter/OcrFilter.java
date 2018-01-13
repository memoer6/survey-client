package com.example.eduardo.survey.filter;

import com.google.android.gms.vision.text.Text;

/**
 * Interface that the concrete OcrFilter family class implement to abstract the diversity of
 * filters (algorithm) in the requester
 *
 */

public interface OcrFilter {

    /* method used by OcrDetectorProcessor to request the capture of key receipt data */
    void filterText(Text line);
}
