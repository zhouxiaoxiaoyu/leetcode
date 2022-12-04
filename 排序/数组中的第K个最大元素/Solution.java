package 排序.数组中的第K个最大元素;

/**
 * @author zhoulyu
 * @create 2022-07-27 13:04
 */
/*
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:
输入: [3,2,1,5,6,4], k = 2
输出: 5
示例2:
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4

提示：

1 <= k <= nums.length <= 105
-104<= nums[i] <= 104

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/kth-largest-element-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    // 思路一：快速排序
    // 每次经过「划分」操作后，我们一定可以确定一个元素的最终位置，如果划分后x最终位置为q，
    // 那么a[l⋯q−1] 中的每个元素小于等于 a[q]，且 a[q]小于等于 a[q+1⋯r] 中的每个元素。
    // 所以只要某次划分的 q 为倒数第 k 个下标的时候，我们就已经找到了答案。
    // 思路二：堆排序
    // 构造k个大小的小顶堆，与堆顶元素(k个元素中最小值)比较，大于最小值，与堆顶元素替换，堆重新排序
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int target = len-k;
        int left = 0, right = len-1;
        while (true) {
            int partition = quick_sort(nums, left, right);
            if (partition == target) return nums[partition];
            if (partition > target) {
                right = partition-1;
            } else {
                left = partition+1;
            }
        }
    }
    public int quick_sort(int[] nums, int left, int right) {
        int base = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= base) {
                right --;
            }
            if (left < right) {
                nums[left] = nums[right];
                left ++;
            }
            while (left < right && nums[left] <= base) {
                left ++;
            }
            if (left < right) {
                nums[right] = nums[left];
                right --;
            }
        }
        nums[left] = base;
        return left;
    }
    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
    public static void main(String[] args) {

    }
}
