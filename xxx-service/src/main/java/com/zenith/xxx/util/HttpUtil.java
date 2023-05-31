// package com.zenith.xxx.util;
//
// import okhttp3.*;
//
// import java.io.IOException;
//
// /**
//  * @author TMW
//  * @since 2023/5/15 9:16
//  */
// public class HttpUtil {
//
//     public static final OkHttpClient client = new OkHttpClient();
// ​
//     public static void get(String url, ICallback_Comm callback) throws IOException {
//         new Thread(){
//             @Override
//             public void run(){
//                 Request request = new Request.Builder()
//                         .url(url)
//                         .build();
// ​
//                 try (Response response = client.newCall(request).execute()) {
//                     String result = response.body().string();
//                     callback.Call(result);
//                 } catch (IOException | ClassNotFoundException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }.start();
// ​
//     }
//     //public static final MediaType JSON  = MediaType.get("application/json; charset=utf-8");
//     public static final MediaType mediaType  = MediaType.get("application/octet-stream; charset=utf-8");
//     public static void post(String url, String param, ICallback_Comm callback) throws IOException {
//         new Thread(){
//             @Override
//             public void run(){
//                 RequestBody body = RequestBody.create(mediaType, param);
//                 Request request = new Request.Builder()
//                         .url(url)
//                         .post(body)
//                         .build();
// ​
//                 try (Response response = client.newCall(request).execute()) {
//                     String result = response.body().string();
//                     callback.Call(result);
//                 } catch (IOException | ClassNotFoundException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }.start();
// ​
//     }
//
// }
