package com.example.tsnt.algorithm.find_max_nums;

import java.util.Arrays;

/**
 * @Author: zhangxiaozong
 * @Date: 2019-01-13 16:13
 * @Description:
 */

public class FindMaxNums {
    public static void main(String[] args) {
        int[] test1 = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2};               // 1, 1, 1, 1, 1, 2, 2, 2, 2, 2
        int[] test2 = {9, 3, 5, 7, 1, 4, 10, 6, 8, 2};              // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        int[] test3 = {9, 8, 11, 17, 25, 0, 4, 5, 6, 7, 8};         // 0, 4, 5, 6, 7, 8, 8, 9, 11, 17, 25
        FindMaxNums sort = new FindMaxNums();
        int num = 6;
        printResult(sort.getMax(test1, num));
        printResult(sort.getMax(test2, num));
        printResult(sort.getMax(test3, num));
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

    public int[] getMax(int[] nums, int size) {
        int[] heap = Arrays.copyOf(nums, size);
        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            maxMin(heap, i, heap.length - 1);
        }

        for (int i = heap.length; i < nums.length; i++) {
            if (nums[i] > heap[0]) {
                heap[0] = nums[i];
                maxMin(heap, 0, heap.length - 1);
            }
        }
        return heap;
    }

    private void maxMin(int[] nums, int index, int limit) {
        int leftIndex = getLeftIndex(index);
        int rightIndex = getRightIndex(index);
        int minIndex = index;
        if (leftIndex <= limit && nums[minIndex] > nums[leftIndex]) {
            minIndex = leftIndex;
        }
        if (rightIndex <= limit && nums[minIndex] > nums[rightIndex]) {
            minIndex = rightIndex;
        }
        if (minIndex != index) {
            swap(nums, index, minIndex);
            maxMin(nums, minIndex, limit);
        }
    }

    private void swap(int[] nums, int a, int b) {
        nums[a] ^= nums[b];
        nums[b] ^= nums[a];
        nums[a] ^= nums[b];
    }

    private int getLeftIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightIndex(int index) {
        return 2 * index + 2;
    }
}
