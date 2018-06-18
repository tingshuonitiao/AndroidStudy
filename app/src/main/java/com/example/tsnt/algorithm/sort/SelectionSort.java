package com.example.tsnt.algorithm.sort;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-06-17 20:18
 * @Description: 选择排序
 */

public class SelectionSort implements SortInterface {
    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    nums[i] ^= nums[j];
                    nums[j] ^= nums[i];
                    nums[i] ^= nums[j];
                }
            }
        }
    }
}
