package com.eyeota.codingfun.cache;

import java.io.IOException;

/**
 * Interface to be implemented by your solution
 */
public interface LookupCache {
    public SegmentConfig[] getSegmentFor(final String orgKey, final String paramKey) throws IOException;
    public SegmentConfig[] getSegmentFor(final String orgKey, final String paramKey, final String paramValKey) throws IOException;
}
