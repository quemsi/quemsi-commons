package com.quemsi.commons.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class StringUtilsTest {
    @Test
    public void testGivenFirstNameAndLastNameWhenCombineThenShouldReturnCombined(){
        String firstName = "Frank";
        String lastName = "Sinetra";
        String combined = StringUtils.combine(firstName, lastName, " ");
        assertThat(combined, equalTo("Frank Sinetra"));
    }

    @Test
    public void testGivenNullFirstNameAndLastNameWhenCombineThenShouldReturnLastName(){
        String firstName = null;
        String lastName = "Sinetra";
        String combined = StringUtils.combine(firstName, lastName, " ");
        assertThat(combined, equalTo("Sinetra"));
    }

    @Test
    public void testGivenEmptyFirstNameAndLastNameWhenCombineThenShouldReturnLastName(){
        String firstName = "";
        String lastName = "Sinetra";
        String combined = StringUtils.combine(firstName, lastName, " ");
        assertThat(combined, equalTo("Sinetra"));
    }

    @Test
    public void testGivenNullLastNameAndFirstNameWhenCombineThenShouldReturnFirtsName(){
        String firstName = "Frank";
        String lastName = null;
        String combined = StringUtils.combine(firstName, lastName, " ");
        assertThat(combined, equalTo("Frank"));
    }

    @Test
    public void testGivenEmptyLastNameAndFirstNameWhenCombineThenShouldReturnFirtsName(){
        String firstName = "Frank";
        String lastName = "";
        String combined = StringUtils.combine(firstName, lastName, " ");
        assertThat(combined, equalTo("Frank"));
    }

    @Test
    public void testTrimWithParentheses() {
        String input = "(getdate())";
        String result = StringUtils.trimSymetric(input, "(", ")");
        assertThat(result, equalTo("getdate()"));
    }

    @Test
    public void testRemovePathPrefixWhenPathStartsWithPrefix() {
        String path = "/api/users/123";
        String prefix = "/api";
        String result = StringUtils.removePathPrefix(path, prefix);
        assertThat(result, equalTo("users/123"));
    }

    @Test
    public void testRemovePathPrefixWhenPathIsNull() {
        String path = null;
        String prefix = "/api";
        String result = StringUtils.removePathPrefix(path, prefix);
        assertThat(result, equalTo(null));
    }

    @Test
    public void testRemovePathPrefixWhenPrefixIsNull() {
        String path = "/api/users/123";
        String prefix = null;
        String result = StringUtils.removePathPrefix(path, prefix);
        assertThat(result, equalTo("api/users/123"));
    }

    @Test
    public void testRemovePathPrefixWhenPathStartsWithTrimmedPrefix() {
        String path = "api/users/123";
        String prefix = "/api/";
        String result = StringUtils.removePathPrefix(path, prefix);
        assertThat(result, equalTo("users/123"));
    }

    @Test
    public void testRemovePathPrefixWhenPathStartsWithSlashAndPrefix() {
        String path = "/api/users/123";
        String prefix = "api";
        String result = StringUtils.removePathPrefix(path, prefix);
        assertThat(result, equalTo("users/123"));
    }

    @Test
    public void testRemovePathPrefixWhenPrefixNotFound() {
        String path = "/api/users/123";
        String prefix = "/v1";
        String result = StringUtils.removePathPrefix(path, prefix);
        assertThat(result, equalTo("api/users/123"));
    }

    @Test
    public void testRemovePathPrefixWithEmptyPrefix() {
        String path = "/api/users/123";
        String prefix = "";
        String result = StringUtils.removePathPrefix(path, prefix);
        assertThat(result, equalTo("api/users/123"));
    }

    @Test
    public void testRemovePathPrefixWithExactMatch() {
        String path = "/api";
        String prefix = "/api";
        String result = StringUtils.removePathPrefix(path, prefix);
        assertThat(result, equalTo(""));
    }    
}
