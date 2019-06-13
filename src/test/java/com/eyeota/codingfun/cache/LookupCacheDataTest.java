package com.eyeota.codingfun.cache;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LookupCacheDataTest {

    private LookupCache lookup;

    public LookupCacheDataTest() throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("out/test/resources/data.json")));
        lookup = new LookupCacheImpl(data);
    }

    @Test
    public void testGetSegmentForOrgParamNameEdu() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("6lkb2cv", "Edu");
        Assert.assertTrue(config.length == 0);
    }

    @Test
    public void testGetSegmentForOrgParamNamePramValHighSchool() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("6lkb2cv", "Edu", "high_school");
        Assert.assertTrue(config.length == 2);
        Assert.assertTrue("intr.edu.scho".equals(config[0].getSegmentId()));
        Assert.assertTrue("intr.edu".equals(config[1].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNamePramValPharmaceutical() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("6lkb2cv", "sub", "Pharmaceutical");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("dem.emp.heal-med-phar".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNamePramValArchitecture() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("6lkb2cv", "sub", "Architecture");
        Assert.assertTrue(config.length == 2);
        Assert.assertTrue("dem.emp.con-arch-des".equals(config[0].getSegmentId()));
        Assert.assertTrue("dem.emp.eng".equals(config[1].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameTestedu() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("n4om0t1", "job_title");
        Assert.assertTrue(config.length == 0);
    }

    @Test
    public void testGetSegmentForOrgParamNameTesteduEmptyVal() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("6lkb2cv", "testedu", "");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("n277".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameTesteduNotEmptyVal() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("n4om0t1", "testedu", "Test");
        Assert.assertTrue(config.length == 0);
    }

    @Test
    public void testGetSegmentForOrgParamNameSidPramVal() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("cj6te90", "sid", "");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("intr.sport".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameGenParamValFemale() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("r8lkb20", "gen", "Female");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("dem.g.f".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameGenParamValMale() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("r8lkb20", "gen", "Male");
        Assert.assertTrue(config.length == 1);
        Assert.assertTrue("dem.g.m".equals(config[0].getSegmentId()));
    }

    @Test
    public void testGetSegmentForOrgParamNameGenParamValIncorrect() throws IOException {
        SegmentConfig[] config = lookup.getSegmentFor("r8lkb20", "gen", "Test");
        Assert.assertTrue(config.length == 0);
    }
}
