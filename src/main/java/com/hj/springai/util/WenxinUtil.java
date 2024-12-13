package com.hj.springai.util;

import com.hj.springai.config.GlobalConfiguration;
import com.hj.springai.config.ConfigConfig;
import com.hj.springai.entity.Wenxin;
import jakarta.annotation.PostConstruct;

import jakarta.annotation.Resource;
import jakarta.websocket.Session;
import okhttp3.*;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @FileName WenxinUtil
 * @Description
 * @Author fazhu
 * @date 2024-12-04
 **/

@Component
public class WenxinUtil {
    private static GlobalConfiguration staticGlobalConfiguration;

    @Resource
    GlobalConfiguration globalConfiguration;

    @PostConstruct
    public void init() {
        staticGlobalConfiguration = globalConfiguration;
    }

    public static String Wenxin(String question) {
        // 加载配置
        Wenxin config = ConfigConfig.loadConfig();
        Wenxin.ChatConfig chatConfig = config.getWenxin().get(staticGlobalConfiguration.getMoxing());//传入使用的模型

        // 构造请求参数
        String accessToken = chatConfig.getParams().get("access_token");
        String urlWithParams = chatConfig.getUrl() + "?access_token=" + accessToken;

        // 构造请求体
        String jsonBody = "{ \"messages\": [ {\"role\": \"user\", \"content\": \" " + question + "\"} ]," +
                "\"system\": \"你是一名学生，现在你需要根据问题整理出相应的笔记，条例清晰，脉络合理\"," +
                "\"stream\":true," +
                "\"max_output_tokens\":100 }";

        // 构造 OkHttp 请求
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));

        Request.Builder requestBuilder = new Request.Builder()
                .url(urlWithParams)
                .method(chatConfig.getMethod(), body);

        // 添加 Headers
        for (Map.Entry<String, String> entry : chatConfig.getHeaders().entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        StringBuilder answer = new StringBuilder();
        // 发送请求并处理响应
        try (Response response = client.newCall(requestBuilder.build()).execute()) {
            if (response.isSuccessful()) {
                try (ResponseBody responseBody = response.body()) {
                    InputStream inputStream = responseBody.byteStream();
                    byte[] buffer = new byte[1024];// 创建字节数组
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        String s = new String(buffer, 0, bytesRead);
                        if (s.contains("result")) {
                            System.out.println(s.split("\"")[19]);// 将字节数组转换为字符串并打印
                            answer.append(s.split("\"")[19]);// 获取响应体内容
                        }
                    }
                }
//                String s = response.body().string();// 获取响应体内容
//                JSONObject jsonObject = new JSONObject(s);// 将响应体内容转换为 JSON 对象
//                String content = jsonObject.getString("result");// 从 JSON 对象中获取 "result" 字段的值
                return answer.toString();
            } else {
                return "请求失败，状态码: " + response.code();
            }
        } catch (Exception e) {
            System.out.println(e);
            return "请求失败 ";
        }
    }
    public static String Wenxin(String question,Session session) {
        // 加载配置
        Wenxin config = ConfigConfig.loadConfig();
        Wenxin.ChatConfig chatConfig = config.getWenxin().get(staticGlobalConfiguration.getMoxing());//传入使用的模型

        // 构造请求参数
        String accessToken = chatConfig.getParams().get("access_token");
        String urlWithParams = chatConfig.getUrl() + "?access_token=" + accessToken;

        // 构造请求体
        String jsonBody = "{ \"messages\": [ {\"role\": \"user\", \"content\": \"" + question.replace("\n","").replace("\r","") + "\"} ]," +
                "\"system\": \"你是一名学生，现在你需要根据问题整理出相应的笔记，条例清晰，脉络合理\"," +
                "\"stream\":true," +
                "\"max_output_tokens\":100 }";
        System.out.println(jsonBody);
        // 构造 OkHttp 请求
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));

        Request.Builder requestBuilder = new Request.Builder()
                .url(urlWithParams)
                .method(chatConfig.getMethod(), body);

        // 添加 Headers
        for (Map.Entry<String, String> entry : chatConfig.getHeaders().entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        StringBuilder answer = new StringBuilder();
        // 发送请求并处理响应
        try (Response response = client.newCall(requestBuilder.build()).execute()) {
            if (response.isSuccessful()) {
                try (ResponseBody responseBody = response.body()) {
                    InputStream inputStream = responseBody.byteStream();
                    byte[] buffer = new byte[1024];// 创建字节数组
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        String s = new String(buffer, 0, bytesRead);
                        if (s.contains("result")) {
                            session.getBasicRemote().sendText(s.split("\"")[19]);// 将字节数组转换为字符串并打印
                            System.out.println(s.split("\"")[19]);// 将字节数组转换为字符串并打印
                            answer.append(s.split("\"")[19]);// 获取响应体内容
                        }
                    }
                }
//                String s = response.body().string();// 获取响应体内容
//                JSONObject jsonObject = new JSONObject(s);// 将响应体内容转换为 JSON 对象
//                String content = jsonObject.getString("result");// 从 JSON 对象中获取 "result" 字段的值
                return answer.toString();
            } else {
                return "请求失败，状态码: " + response.code();
            }
        } catch (Exception e) {
            System.out.println(e);
            return "请求失败 ";
        }
    }

}