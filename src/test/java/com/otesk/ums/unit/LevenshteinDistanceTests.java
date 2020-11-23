package com.otesk.ums.unit;

import com.otesk.ums.searchfilters.utils.SearchFilterByUsernameUtil;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
public class LevenshteinDistanceTests {

    @Test
    public void calculateLevenshteinDistanceBetweenDifferentWordsTest() {
        assertEquals(SearchFilterByUsernameUtil.calculateLevenshteinDistance("user", "lockdown"), 8);
    }

    @Test
    public void calculateLevenshteinDistanceBetweenTheSameWordsTest() {
        assertEquals(SearchFilterByUsernameUtil.calculateLevenshteinDistance("user", "user"), 0);
    }

    @Test
    public void calculateLevenshteinDistanceBetweenTheSimilarWordsTest() {
        assertEquals(SearchFilterByUsernameUtil.calculateLevenshteinDistance("user", "lockeduser"), 6);
    }
}