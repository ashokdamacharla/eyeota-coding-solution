package com.eyeota.codingfun.cache;

public class SegmentConfig {
    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    private String segmentId;

    @Override
    public String toString() {
        return "SegmentConfig{" +
                "segmentId='" + segmentId + '\'' +
                '}';
    }
}
