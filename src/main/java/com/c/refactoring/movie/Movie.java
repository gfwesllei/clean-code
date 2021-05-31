package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Movie {

    String rating;

    private static final List<String> VALID_B_RATINGS_LIST
            = Arrays.asList("B1","B2","B3","B4");

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents 
    any digit between 1 and 4*/
    public boolean isValidRating() {

        if(this.getRating() == null)
            return false;

        if (isValidB())
            return true;

        return isValidA();
    }

    private boolean isValidA() {

        return firstCharIs("A")
                && this.getRating().length() == 3
                && StringUtils.isNumeric(this.getRating().substring(1, 3));
    }

    boolean isValidB(){
        return VALID_B_RATINGS_LIST.contains(this.getRating());
    }

    private boolean firstCharIs(String firstChar) {
        return this.getRating().substring(0, 1).equalsIgnoreCase(firstChar);
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
