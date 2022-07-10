package com.tianscar.qqbot.image;

import com.tianscar.qqbot.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import javax.imageio.ImageIO;

public class Pa implements ImageProcessor {

    private static final Random random = new Random();

    private BufferedImage logo, image;
    
    public Pa(long qq) throws IOException {
        logo = ImageIO.read(new URL("https://q1.qlogo.cn/g?b=qq&nk=" + qq + "&s=640"));
        image = ImageIO.read(Objects.requireNonNull(Pa.class.getResourceAsStream("/images/pa/pa_" + Utils.randomInt(random, 0, 48) + ".jpg")));
    }

    @Override
    public BufferedImage process() {
        logo = ImageUtils.circleImage(logo);
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int w = image.getWidth();
        int h = image.getHeight();
        g.drawImage(logo,0, h - h / 5,w / 5,h / 5,null);
        g.dispose();
        return image;
    }

}