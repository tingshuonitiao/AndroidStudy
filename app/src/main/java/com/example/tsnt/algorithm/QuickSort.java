package com.example.tsnt.algorithm;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-06-03 22:43
 * @Description: 快速排序
 * <p>
 * 可以优化地方:
 * 1. 选主元, 随机选、中位数
 * 2. 子集很小的时候, 使用简单排序(比如插入排序)
 * 3. 三路划分
 */

public class QuickSort {
    /**
     * source: https://www.cnblogs.com/skywang12345/p/3596746.html
     */
    class TheOtherSolution1 {
        /**
         * @param a 待排序的数组
         * @param l 数组的左边界(例如，从起始位置开始排序，则l=0)
         * @param r 数组的右边界(例如，排序截至到数组末尾，则r=a.length-1)
         */
        public void quickSort(int[] a, int l, int r) {
            if (l < r) {
                int i, j, x;
                i = l;
                j = r;
                x = a[i];
                while (i < j) {
                    while (i < j && a[j] > x)
                        j--; // 从右向左找第一个小于x的数
                    if (i < j)
                        a[i++] = a[j];
                    while (i < j && a[i] < x)
                        i++; // 从左向右找第一个大于x的数
                    if (i < j)
                        a[j--] = a[i];
                }
                a[i] = x;
                quickSort(a, l, i - 1); /* 递归调用 */
                quickSort(a, i + 1, r); /* 递归调用 */
            }
        }
    }

    /**
     * source: https://zhuanlan.zhihu.com/p/34787695
     */
    class TheOtherSolution2 {
        /**
         * 快速排序
         *
         * @param arr 待排序的数组
         * @param L   指向数组第一个元素
         * @param R   指向数组最后一个元素
         */
        public void quickSort(int[] arr, int L, int R) {
            int i = L;
            int j = R;
            //支点
            int pivot = arr[(L + R) / 2];
            //左右两端进行扫描，只要两端还没有交替，就一直扫描
            while (i <= j) {
                //寻找直到比支点大的数
                while (pivot > arr[i])
                    i++;
                //寻找直到比支点小的数
                while (pivot < arr[j])
                    j--;
                //此时已经分别找到了比支点小的数(右边)、比支点大的数(左边)，它们进行交换
                if (i <= j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }
            //上面一个while保证了第一趟排序支点的左边比支点小，支点的右边比支点大了。
            //“左边”再做排序，直到左边剩下一个数(递归出口)
            if (L < j)
                quickSort(arr, L, j);
            //“右边”再做排序，直到右边剩下一个数(递归出口)
            if (i < R)
                quickSort(arr, i, R);
        }
    }
}
