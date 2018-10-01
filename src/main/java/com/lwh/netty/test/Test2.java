package com.lwh.netty.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lwh
 * @date 2018-10-01
 * @desp
 */
public class Test2 {

    private static final int[] SIZE_TABLE;

    static {
        List<Integer> sizeTable = new ArrayList<Integer>();
        for (int i = 16; i < 512; i += 16) {
            sizeTable.add(i);
        }

        for (int i = 512; i > 0; i <<= 1) {
            sizeTable.add(i);
        }

        SIZE_TABLE = new int[sizeTable.size()];
        for (int i = 0; i < SIZE_TABLE.length; i ++) {
            SIZE_TABLE[i] = sizeTable.get(i);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(SIZE_TABLE));
    }
}
