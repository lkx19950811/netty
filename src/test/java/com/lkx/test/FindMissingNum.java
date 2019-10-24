package com.lkx.test;

/**
 * 寻找第一个丢失的正整数
 * @author: lkx
 * @email: lkx19950811@163.com
 * @date: create in 9:35 2019/10/23
 */
public class FindMissingNum {
    public static void main(String[] args) {
        int[] a = {1,2,0};
        int[] b = {3,4,-1,1};
        int[] c = {7,8,9,11,12};
        System.out.println(findMissing(a));
        System.out.println(findMissing(b));
        System.out.println(findMissing(c));
        int[] d = {1};
        System.out.println(findMissing(d));
    }
    private static int findMissing(int[] nums){
        int n = nums.length +1;
        int min_missing = 0;
        int[] bitMap = new int[nums.length];
        for (int num : nums){
            if (num>0 && num <n){
                bitMap[num-1] = bitMap[num-1] + 1;
            }
        }
        for (int i =0;i<bitMap.length;i++){
            if (bitMap[i]==0){
                min_missing = i + 1;
                break;
            }
            //已经进行到底部了
            min_missing = bitMap.length + 1;
        }
        //如果全部大于 n,才会出现min_missing=0的情况
        if (min_missing==0){
            min_missing =1;
        }
        return min_missing;
    }
}
