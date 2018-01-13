package com.example.eduardo.survey;


import android.util.Log;
import android.util.SparseArray;


import com.example.eduardo.survey.filter.OcrFilter;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;

import java.util.List;


/**
 * A very simple Processor which gets detected TextBlocks
  */
public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private OcrFilter mOcrFilter;


    OcrDetectorProcessor(OcrFilter filter) {
        this.mOcrFilter = filter;
    }


    @Override
    public void release() {

    }


    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {

        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {

                // Break the text into multiple lines
                List<? extends Text> lines = item.getComponents();
                for (Text line : lines) {

                    //Log.d("OcrDetectorProcessor", "Text detected: " + line.getValue());

                    mOcrFilter.filterText(line);

                }

            }
        }
    }

}

