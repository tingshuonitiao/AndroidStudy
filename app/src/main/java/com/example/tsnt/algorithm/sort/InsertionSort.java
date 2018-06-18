package com.example.tsnt.algorithm.sort;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-06-17 17:20
 * @Description: 插入排序
 */

public class InsertionSort implements SortInterface {
    @Override
    public void sort(int[] nums) {
        if (nums == null) return;
        for (int i = 1; i < nums.length; i++) {
            int insertNum = nums[i];
            int j = i - 1;
            for (; j >= 0 && insertNum < nums[j]; j--) {
                // 从后往前遍历, 和已经排序好的数进行比较
                // 如果小于排序好的数, 排序好的数后移一位
                nums[j + 1] = nums[j];
            }
            // 如果大于等于排序好的数, 插入
            nums[j + 1] = insertNum;
        }
    }
}
