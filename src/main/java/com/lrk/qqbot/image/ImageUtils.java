package com.lrk.qqbot.image;

import java.awt.image.*;
import java.awt.geom.*;
import java.awt.*;

public class ImageUtils {

    public static BufferedImage circleImage(BufferedImage rawImage){
        int size = Math.min(rawImage.getWidth(), rawImage.getHeight());
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Ellipse2D.Double shape = new Ellipse2D.Double(0,0, size, size);
        g.setClip(shape);
        g.drawImage(rawImage,0,0, size, size, null);
        g.dispose();
        return image;
    }

}