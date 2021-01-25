package nio;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.GzipCompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class HttpClientDemo {
    public static CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * 发送get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String getRequest(String url) throws IOException {
        System.out.println("开始发起get请求，url=" + url);
        HttpGet httpGet = new HttpGet(url);
        try(CloseableHttpResponse response1 = httpclient.execute(httpGet);) {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            return EntityUtils.toString(entity1, "UTF-8");
        }
    }

    /**
     * 发送post请求
     * @param url
     * @param json 入参json
     * @param headers 消息头
     * @return
     * @throws IOException
     */
    public static String postRequest(String url, String json, Map<String, String> headers) throws IOException {
        System.out.println("开始发送post请求，入参：" + json);
        HttpPost httpPost = new HttpPost(url);
        Set<String> keySet = headers.keySet();
        for (String name : keySet) {
            httpPost.setHeader(name, headers.get(name));
        }
        final String JSON_TYPE = "application/json;charset=UTF-8";
        httpPost.setHeader(HTTP.CONTENT_TYPE, JSON_TYPE);
        StringEntity entity = new StringEntity(json, "UTF-8");

        httpPost.setHeader("ICK-Content-Encoding", "gzip");
        httpPost.setEntity(new GzipCompressingEntity(entity));

        entity.setContentType(JSON_TYPE);
        entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, JSON_TYPE));
        httpPost.setEntity(entity);

        try(CloseableHttpResponse response1 = httpclient.execute(httpPost);) {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            return EntityUtils.toString(entity1, "UTF-8");
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801";
        String response = getRequest(url);
//        String json = "{\"name\":\"xiaoming\"}";
//        Map<String, String> headers = Collections.singletonMap("userName","zhangsan");
//        String response = postRequest(url, json, headers);
        System.out.println("服务器返回：" + response);
    }

}
