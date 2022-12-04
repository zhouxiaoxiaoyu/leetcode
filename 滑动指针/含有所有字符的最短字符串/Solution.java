package 滑动指针.含有所有字符的最短字符串;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author zhoulyu
 * @create 2022-07-05 16:48
 */
class Solution {
    public String minWindow(String s, String t) {
        int i = 0, j = 0;
        //记录字符个数,字符数相等时说明找到了一个类别
        int[] smap = new int[60], tmap = new int[60];
        //cat记录字符种类个数,为0表示找到了一个子字符串
        int cat = 0, slen = s.length(), tlen = t.length();
        //记录最短长度和字符串
        int min = slen+1;
        String res = "";
        //记录要找的字符串t的每个字符数量
        for (int k = 0; k < tlen; k ++) {
            tmap[t.charAt(k)-'A']++;

        }
        //计算字符种类
        for (int k = 0; k < 60; k ++) {
            if (tmap[k] != 0) cat ++;
        }


        while (j < slen || i < slen) {
            //移动右边界至找到一个子字符串,同时记录字符个数与要找的字符串对比,个数相等cat减1
            while (j < slen) {
                if (cat == 0) {
                    break;
                }
                int x = s.charAt(j)-'A';
                smap[x] ++;
                if (smap[x] == tmap[x]) cat --;
                j ++;
            }
            //左边界右移至不在满足子字符串为止,同时记录最短子字符串
            while (i < slen) {
                if (j-i < min && cat == 0) {
                    min = j-i;
                    res = s.substring(i, j);
                }
                int x = s.charAt(i)-'A';
                smap[x] --;
                i ++;
                if (smap[x] < tmap[x]) {
                    cat ++;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        s = s.substring(1, s.length()-1);
        String t = sc.nextLine();
        t = t.substring(1, t.length()-1);
        Solution solu = new Solution();
        String res = solu.minWindow(s, t);
        System.out.println(res);
    }
}
