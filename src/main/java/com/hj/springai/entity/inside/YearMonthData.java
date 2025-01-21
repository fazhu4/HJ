package com.hj.springai.entity.inside;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName YearMonthData
 * @Description
 * @Author fazhu
 * @date 2025-01-21
 **/
@Data
public class YearMonthData {
    private Map<String, Map<String, List<Integer>>> data = new HashMap<>();

    @JsonAnySetter
    public void add(String key, Map<String, List<Integer>> value) {
        data.put(key, value);
    }


}

