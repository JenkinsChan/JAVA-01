package nio;

import okhttp3.*;
import java.io.IOException;

public class OkHttpDemo {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static OkHttpClient client = new OkHttpClient();

    /**
     * 发送get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String getRequest(String url) throws IOException {
        System.out.println("开始发起get请求");
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 发送post请求
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String postRequest(String url, String json) throws IOException {
        System.out.println("开始发起post请求，入参=" + json);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801";
//        String response = getRequest(url);
        String json = "{\"name\":\"xiaoming\"}";
        String response = postRequest(url, json);
        System.out.println("服务器返回：" + response);
    }
}
