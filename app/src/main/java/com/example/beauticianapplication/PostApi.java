package com.example.beauticianapplication;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostApi {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String re =  response.body().string();
            re = re.replace("\\u011f", "ğ");
            re = re.replace("\\u011e", "Ğ");
            re = re.replace("\\u0131", "ı");
            re = re.replace("\\u0130", "İ");
            re = re.replace("\\u00f6", "ö");
            re = re.replace("\\u00d6", "Ö");
            re = re.replace("\\u00fc", "ü");
            re = re.replace("\\u00dc", "Ü");
            re = re.replace("\\u015f", "ş");
            re = re.replace("\\u015e", "Ş");
            re = re.replace("\\u00e7", "ç");
            re = re.replace("\\u00c7", "Ç");



            return  re;
        }
    }

    public String whenGetRequest_thenCorrect(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        String re =  response.body().string();
        re = re.replace("\\u011f", "ğ");
        re = re.replace("\\u011e", "Ğ");
        re = re.replace("\\u0131", "ı");
        re = re.replace("\\u0130", "İ");
        re = re.replace("\\u00f6", "ö");
        re = re.replace("\\u00d6", "Ö");
        re = re.replace("\\u00fc", "ü");
        re = re.replace("\\u00dc", "Ü");
        re = re.replace("\\u015f", "ş");
        re = re.replace("\\u015e", "Ş");
        re = re.replace("\\u00e7", "ç");
        re = re.replace("\\u00c7", "Ç");
        return re;
    }



}