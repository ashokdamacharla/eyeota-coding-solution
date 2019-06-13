package com.eyeota.codingfun.cache;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LookupCacheSampleDataTest {

    private LookupCache lookup;

    public LookupCacheSampleDataTest() throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("out/test/resources/sampledata.json")));
        lookup = new LookupCacheImpl(data);
    }

    @Test
    public void testGetSegmentForOrgParamName() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "paramName1");
        Assert.assertTrue(config.length == 0);
    }

    @Test
    public void testGetSegmentForOrgParamNamePramVal1() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "paramName1", "paramVal1");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("seg_1234".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNamePramVal2To5() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "paramName1", "paramVal5");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("intr.edu".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNamePramVal6() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "paramName1", "paramVal6");
        Assert.assertTrue(config.length == 3);
        Assert.assertTrue("dem.infg.m".equals(config[0].getSegmentId()));
        Assert.assertTrue("intr.heal".equals(config[1].getSegmentId()));
        Assert.assertTrue("dem.infg.f".equals(config[2].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameTestedu() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "testedu");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("n277".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameTesteduEmptyVal() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "testedu", "");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("n277".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameTesteduNotEmptyVal() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "testedu", "Test");
        Assert.assertTrue(config.length == 0);
    }

    @Test
    public void testGetSegmentForOrgParamNameSidPramVal() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "sid", "");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("dem.life.expat".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameGenParamValFemale() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "gen", "Female");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("dem.g.f".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameGenParamValMale() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "gen", "Male");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("dem.g.m".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameGenParamValIncorrect() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("org1", "gen", "Test");
        Assert.assertTrue(config.length == 0);
    }
}
