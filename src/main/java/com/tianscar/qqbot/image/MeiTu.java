package com.tianscar.qqbot.image;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MeiTu implements ImageProcessor {

    public static final String[] links = {
            "https://iw233.cn/api.php?sort=random&type=json",
            "http://api.iw233.cn/api.php?sort=random&type=json",
            "http://skri.iw233.cn/api.php?sort=random&type=json",
            "http://aqua.iw233.cn/api.php?sort=random&type=json",
            "https://dev.iw233.cn/api.php?sort=random&type=json",
            "https://mirlkoi.ifast3.vipnps.vip/api.php?sort=random&type=json"
    };

    @Override
    public BufferedImage process() {
        try {
            return getImage();
        } catch (IOException ignored) {
            return null;
        }
    }

    private static BufferedImage getImage() throws IOException {
        URL url = new URL(links[1]);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        JsonObject result = JsonParser.parseReader(new InputStreamReader(connection.getInputStream())).getAsJsonObject();
        connection.disconnect();
        return ImageIO.read(new URL(result.getAsJsonArray("pic").get(0).getAsString()));
    }

}
