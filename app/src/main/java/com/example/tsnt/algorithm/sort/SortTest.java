package com.example.tsnt.algorithm.sort;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-06-17 17:43
 * @Description: 排序测试
 */

public class SortTest {

    public static void main(String[] args) {
        int[] test1 = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
        int[] test2 = {9, 3, 5, 7, 1, 4, 10, 6, 8, 2};
        int[] test3 = {9, 8, 11, 17, 25, 0, 4, 5, 6, 7, 8};
        SortInterface sort = new RadixSort();
        sort.sort(test1);
        sort.sort(test2);
        sort.sort(test3);
        printResult(test1);
        printResult(test2);
        printResult(test3);
    }

    /**
     * 打印出结果
     *
     * @param nums
     */
    public static void printResult(int[] nums) {
        if (nums == null) System.out.println("result: ");
        StringBuilder stringBuilder = new StringBuilder();
        if (nums.length > 0) {
            stringBuilder.append(nums[0]);
        }
        for (int i = 1; i < nums.length; i++) {
            stringBuilder.append(", " + nums[i]);
        }
        System.out.println("result: " + stringBuilder.toString());
    }
}
