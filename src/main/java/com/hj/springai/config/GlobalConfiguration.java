package com.hj.springai.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @FileName GlobalConfiguration
 * @Description
 * @Author fazhu
 * @date 2024-12-05
 **/

@Data
@Component
public class GlobalConfiguration {
    //设置模型
    @Value("${wenxin.moxing}")
    private String moxing;

    @Value("${custom-config. project:.project-name}")
    private String projectName;

}
