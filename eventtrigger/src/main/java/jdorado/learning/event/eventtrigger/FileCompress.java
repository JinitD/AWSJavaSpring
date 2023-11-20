package jdorado.learning.event.eventtrigger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import net.coobird.thumbnailator.Thumbnails;

public class FileCompress {


    public static ArrayList<Object> compressAndCreateThumbnail(InputStream imageStream, LambdaLogger logger)
            throws Exception {

        ArrayList<Object> objectImg = new ArrayList<>();

        ByteArrayOutputStream compressedImageStream = new ByteArrayOutputStream();

        Thumbnails.of(imageStream)
                .size(300, 300) // Reemplaza con el tamaño deseado
                .outputFormat("jpeg") // Reemplaza con el formato deseado
                .toOutputStream(compressedImageStream);

        InputStream compressedImageInputStream = new ByteArrayInputStream(compressedImageStream.toByteArray());
        objectImg.add(compressedImageInputStream);
        objectImg.add(compressedImageStream.size());

        logger.log(
                "esto es el compressedImageInputStream " + objectImg.get(0) + "    y esto size   " + objectImg.get(1));

        return objectImg;
    }
}
