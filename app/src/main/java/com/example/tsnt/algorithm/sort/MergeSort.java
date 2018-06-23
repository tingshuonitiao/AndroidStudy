package com.example.tsnt.algorithm.sort;

import java.util.Arrays;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-06-23 22:26
 * @Description: 归并排序
 */

public class MergeSort implements SortInterface {

    @Override
    public void sort(int[] nums) {
        if (nums == null) return;
        int[] copy = Arrays.copyOf(nums, nums.length);
        mergeSort(nums, copy, 0, nums.length - 1);
    }

    public void mergeSort(int[] target, int[] copy, int start, int end) {
        if (start >= end) return;
        int mid = (end - start) / 2 + start;
        mergeSort(target, copy, start, mid);
        mergeSort(target, copy, mid + 1, end);
        merge(target, copy, start, mid, end);
    }

    public void merge(int[] target, int[] copy, int left, int mid, int right) {
        int s1 = left;
        int s2 = mid + 1;
        int index = left;
        while (s1 <= mid && s2 <= right) {
            target[index++] = copy[s1] <= copy[s2] ? copy[s1++] : copy[s2++];
        }
        while (s1 <= mid) {
            target[index++] = copy[s1++];
        }
        while (s2 <= right) {
            target[index++] = copy[s2++];
        }
        for (int m = left; m <= right; m++) {
            copy[m] = target[m];
        }
    }
}
