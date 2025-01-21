package com.hj.springai.Service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @FileName TimeService
 * @Description
 * @Author fazhu
 * @date 2025-01-20
 **/
@Service
public class TimeService {
    //字符串转数组
    public ArrayList<String> strToArr(String str){
        String[] s=str.split(",");
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(s));
        return arr;
    }

    //数组转字符串
    public String arrToStr(ArrayList<String> arr){
        if(arr.size()==1){
            return arr.get(0);
        }
        String str="";
        for(int i=0;i<arr.size()-1;i++){
            str+=arr.get(i)+",";
        }
        str+=arr.get(arr.size()-1);
        return str;
    }

    //添加签到时间
    public void addTime(String year,String month,int day){

    }



}
