package com.example.tsnt.algorithm.sort;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-06-17 20:08
 * @Description: 冒泡排序
 */

public class BubbleSort implements SortInterface {
    @Override
    public void sort(int[] nums) {
        if (nums == null) return;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    nums[j - 1] ^= nums[j];
                    nums[j] ^= nums[j - 1];
                    nums[j - 1] ^= nums[j];
                }
            }
        }
    }
}
