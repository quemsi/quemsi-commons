package com.biddflux.commons.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class SizeParserTest {
    @Test
    public void testGiven250MBThenShouldReturn(){
        long bytes = SizeParser.parse("250", "MB");
        assertThat(bytes, equalTo(262144000L));
    }
    @Test
    public void testGivenBytesTo250MBWhenToStringThenShouldReturn250MB(){
        String size = SizeParser.toString(262144000L);
        assertThat(size, equalTo("250 MB"));
    }
    @Test
    public void testGiven15GBThenShouldReturn(){
        long bytes = SizeParser.parse("15", "GB");
        assertThat(bytes, equalTo(16106127360L));
    }
    @Test
    public void testGivenBytesTo15GBWhenToStringThenShouldReturn15GB(){
        String size = SizeParser.toString(16106127360L);
        assertThat(size, equalTo("15 GB"));
    }
    @Test
    public void testGivenBytesTo150GBWhenToStringThenShouldReturn150GB(){
        String size = SizeParser.toString(161061273600L);
        assertThat(size, equalTo("150 GB"));
    }
    @Test
    public void testGivenArbitaryBytesWhenToStringThenShouldReturnDecimalGB(){
        String size = SizeParser.toString(26106127360L);
        assertThat(size, equalTo("24.3 GB"));
    }
}
