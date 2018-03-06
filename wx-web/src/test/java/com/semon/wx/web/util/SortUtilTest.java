package com.semon.wx.web.util;

import com.semon.common.util.SortUtil;
import org.junit.Assert;
import org.junit.Test;

public class SortUtilTest {
    private static final String HELLO = "hello";
    private static final String WORLD = "world";
    private static final String AYA = "aya";
    private static final String HOW = "how";
    private static final String INDEX = "index";

    /**
     * wether arr changes after sort
     */
    @Test
    public void bydic() {
        String[] arr = {WORLD, HELLO, INDEX, AYA, HOW};
        SortUtil.bydic(arr);
        Assert.assertArrayEquals(arr, new String[]{AYA, HELLO, HOW, INDEX, WORLD});
    }
}