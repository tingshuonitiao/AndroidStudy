package com.example.tsnt.algorithm.sort;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-06-18 09:46
 * @Description: 堆排序
 */

public class HeapSort implements SortInterface {
    @Override
    public void sort(int[] nums) {
        // 初始化大顶堆
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            // 从第一个非叶子节点开始遍历
            maxToTop(nums, i, nums.length);
        }
        // 正式开始排序
        for (int i = nums.length - 1; i > 0; i--) {
            // 将最大值从顶部放到最后一位
            swap(nums, 0, i);
            // 交换后将剩余的数重新堆化
            maxToTop(nums, 0, i);
        }
    }

    /**
     * 使大的数位于顶端
     *
     * @param nums
     * @param currIndex
     * @param limitIndex
     */
    public void maxToTop(int[] nums, int currIndex, int limitIndex) {
        int leftIndex = getLeftIndex(currIndex);
        int rightIndex = getRightIndex(currIndex);
        int maxIndex = currIndex;
        if (leftIndex < limitIndex && nums[leftIndex] > nums[maxIndex]) {
            maxIndex = leftIndex;
        }
        if (rightIndex < limitIndex && nums[rightIndex] > nums[maxIndex]) {
            maxIndex = rightIndex;
        }
        if (maxIndex != currIndex) {
            // 说明有子节点大于当前节点, 交换
            swap(nums, maxIndex, currIndex);
            // 继续查看是否需要交换
            maxToTop(nums, maxIndex, limitIndex);
        }
    }

    /**
     * 交换位置
     *
     * @param nums
     * @param a
     * @param b
     */
    public void swap(int[] nums, int a, int b) {
        nums[a] ^= nums[b];
        nums[b] ^= nums[a];
        nums[a] ^= nums[b];
    }


    /**
     * 获取左子节点的下标
     *
     * @param curr
     */
    public int getLeftIndex(int curr) {
        return 2 * curr + 1;
    }

    /**
     * 获取右子节点的下标
     *
     * @param curr
     */
    public int getRightIndex(int curr) {
        return 2 * curr + 2;
    }
}
