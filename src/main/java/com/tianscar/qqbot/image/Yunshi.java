package com.tianscar.qqbot.image;


import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import javax.imageio.ImageIO;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianscar.qqbot.Data;

public class Yunshi implements ImageProcessor {

    private static final int TITLE_SIZE = 45, TEXT_SIZE = 25;
    private static final Color TITLE_COLOR = Color.decode("#F5F5F5"), TEXT_COLOR = Color.decode("#323232");
    private static final Random random = new Random();
    private static final Font titleFont, textFont;
    static {
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(Yunshi.class.getResourceAsStream("/fonts/droid_sans.ttf"))).deriveFont(Font.BOLD, TITLE_SIZE);
            textFont = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(Yunshi.class.getResourceAsStream("/fonts/droid_sans.ttf"))).deriveFont(Font.PLAIN, TEXT_SIZE);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final BufferedImage image;
    private final JsonArray title;
    private final JsonArray text;
    
    public Yunshi() throws IOException {
        image = ImageIO.read(Objects.requireNonNull(Yunshi.class.getResourceAsStream("/images/yunshi/frame_" + (random.nextInt(66) + 1) + ".jpg")));
        JsonObject tmp = (JsonObject) JsonParser.parseString(new String(Objects.requireNonNull(Data.getResourceBytes("/json/yunshi/title.json"))));
        title = tmp.get("types_of").getAsJsonArray();
        tmp = (JsonObject)JsonParser.parseString(new String(Objects.requireNonNull(Data.getResourceBytes("/json/yunshi/text.json"))));
        text = tmp.get("copywriting").getAsJsonArray();
    }

    @Override
    public BufferedImage process() {
        //获取数据
        JsonObject mTitle = title.get((int)(Math.random()*(title.size()-1))).getAsJsonObject();
        int luck = mTitle.get("good-luck").getAsInt();
        String yunshi = mTitle.get("name").getAsString();
        String zhufu = "";
        for (JsonElement jsonElement : text) {
            JsonObject obj = (JsonObject) jsonElement;
            if (obj.get("good-luck").getAsInt() == luck) {
                zhufu = obj.get("content").getAsString();
                break;
            }
        }
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //绘制Title
        g.setFont(titleFont);
        g.setColor(TITLE_COLOR);
        switch(yunshi.length()) {
            case 1:
                g.drawString(yunshi, 115, 116);
                break;
            case 2:
                g.drawString(yunshi, 95, 116);
                break;
            case 3:
                g.drawString(yunshi, 75, 116);
                break;
        }
        //绘制Text
        g.setFont(textFont);
        g.setColor(TEXT_COLOR);
        drawText(g, 85, 200, zhufu);
        g.dispose();
        return image;
    }
    private void drawText(Graphics2D g, int x, int y, String str){
        int index = 0;
        int y_raw = y;
        for (String s : str.split("")) {
            g.drawString(s, x, y);
            y += g.getFontMetrics().stringWidth(s);
            if(++index > 8){
                x+=g.getFontMetrics().stringWidth(s) + 1;
                index = 0;
                y = y_raw;
            }
        }
    }

}