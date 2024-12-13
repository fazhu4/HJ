//ConfigLoader
package com.hj.springai.config;

import com.hj.springai.entity.Wenxin;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

//资源加载配置
public class ConfigConfig {
    public static Wenxin loadConfig() {
        //放在try里面自动关闭资源
        try (InputStream inputStream = ConfigConfig.class.getClassLoader().getResourceAsStream("Wenxin-api.yml")) {
            if (inputStream == null) {
                //空则异常
                throw new RuntimeException("配置文件未找到！");
            }
            Yaml yaml = new Yaml();
            //yaml.loadAs将输入流映射到WenxinConfig对象
            return yaml.loadAs(inputStream, Wenxin.class);
        } catch (Exception e) {
            throw new RuntimeException("加载配置文件失败", e);
        }
    }
}
