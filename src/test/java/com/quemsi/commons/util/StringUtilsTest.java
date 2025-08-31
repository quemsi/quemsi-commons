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
}
