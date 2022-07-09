package com.lrk.qqbot.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Diu implements ImageProcessor {

    BufferedImage logo, image;
    
    public Diu(long qq) throws IOException {
        logo = ImageIO.read(new URL("https://q1.qlogo.cn/g?b=qq&nk=" + qq + "&s=100"));
        image = ImageIO.read(Objects.requireNonNull(Diu.class.getResourceAsStream("/images/diu/diu.png")));
    }

    @Override
    public BufferedImage getImage() {
        logo = ImageUtils.circleImage(image);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(logo,9,177,147,147,null);
        g.dispose();
        return image;
    }

}