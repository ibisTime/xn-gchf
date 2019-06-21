package com.cdkj.gchf.common;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportUtil {

    private final static Logger log = LoggerFactory.getLogger(ImportUtil.class);

    public static <E> String checkRepeat(List<E> dateList, String columnName) {

        if (CollectionUtils.isEmpty(dateList)) {
            return null;
        }

        try {
            List<String> tempList = new ArrayList<>();
            String getMethod = "get" + columnName.substring(0, 1).toUpperCase()
                    + columnName.substring(1);

            for (E date : dateList) {

                Method method = date.getClass().getMethod(getMethod);

                String tempValue = method.invoke(date).toString();

                if (!tempList.contains(tempValue)) {
                    tempList.add(tempValue);
                } else {
                    return tempValue;
                }

            }
        } catch (Exception e) {
            log.info("导入数据校验失败:{}", e.getMessage());
        }

        return null;

    }


    /**
     * 实现图像的等比缩放
     */
    private static BufferedImage resize(BufferedImage source, int targetW,
            int targetH) {
        // targetW，targetH分别表示目标长和宽
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
        // 则将下面的if else语句注释即可
        if (sx < sy) {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
                    targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(targetW, targetH, type);
        }
        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    public static void main(String[] args) {
//        File file = new File("C:\\Users\\old3\\Downloads\\02.png");
        File file = FileUtils.getFile("C:\\Users\\old3\\Downloads\\001.png");
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
            BufferedImage resize = resize(bufferedImage, 600, 400);
            String fileName = file.getName();
            String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
            ImageIO.write(resize, formatName, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
