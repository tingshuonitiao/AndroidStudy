package com.example.tsnt.algorithm.sort;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-06-24 21:58
 * @Description: 基数排序
 */

public class RadixSort implements SortInterface {
    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        // 找到最大的数
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        // 确定最多几位
        int k = 1;
        while (max / 10 > 0) {
            max /= 10;
            k++;
        }
        // 初始化桶
        int[][] buckets = new int[10][nums.length];
        int base = 10;
        // 桶的计数位
        int[] index = new int[10];
        for (int i = 0; i < k; i++) {
            // 分配到bucket中
            for (int j = 0; j < nums.length; j++) {
                int bucketNo = nums[j] % base / (base / 10);
                buckets[bucketNo][index[bucketNo]++] = nums[j];
            }
            // 将数字收集回到nums
            int m = 0;
            for (int j = 0; j < buckets.length; j++) {
                for (int l = 0; l < index[j]; l++) {
                    nums[m++] = buckets[j][l];
                }
                index[j] = 0;
            }
            // 将桶里计数器置0，用于下一次位排序
            base *= 10;
        }
    }
}
