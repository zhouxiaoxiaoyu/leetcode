package 排序.前K个高频元素;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author zhoulyu
 * @create 2022-07-26 8:40
 */
/*
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

示例 1:
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:
输入: nums = [1], k = 1
输出: [1]

提示：
1 <= nums.length <= 105
k 的取值范围是 [1, 数组中不相同的元素的个数]
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的

进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n是数组大小。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/top-k-frequent-elements
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            if (!map.containsKey(num)) map.put(num, 1);
            else map.put(num, map.get(num)+1);
        }
        //最小堆
        PriorityQueue<Integer> pri = new PriorityQueue<>((a, b)-> map.get(a)-map.get(b));
        for (Integer key: map.keySet()) {
            if (pri.size() < k) {
                pri.add(key);
                //与堆顶比较 大于等于最小的数就替换
                //维护堆的大小为k  时间复杂度为O(logk)
            } else if (map.get(key) >= map.get(pri.peek())) {
                pri.poll();
                pri.add(key);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i ++) {
            res[i] = pri.poll();
        }
        return res;

    }
    public static void main(String[] args) {
        Solution solu = new Solution();
        int[] res = solu.topKFrequent(new int[]{1,1,1,2,2,3,2}, 2);
        for (int num: res) {
            System.out.println(num+" ");
        }
    }
}
