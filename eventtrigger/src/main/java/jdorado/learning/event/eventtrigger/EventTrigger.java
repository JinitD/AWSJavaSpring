package jdorado.learning.event.eventtrigger;

import java.io.InputStream;
import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

public class EventTrigger implements RequestHandler<S3EventNotification, String> {

    private final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
    private final String putBucket = "bucket-compress-image-jdorado";

    @Override
    public String handleRequest(S3EventNotification input, Context context) {

        LambdaLogger logger = context.getLogger(); // Create the logger LambdaLogger
        logger.log("========== SE SUBIO SEVERA FOTO SEXY A MI S3 😏 ===========");

        try {
            for (S3EventNotification.S3EventNotificationRecord record : input.getRecords()) {
                FileDTO fileDto = new FileDTO(record);
                logger.log(fileDto.toString());

                // Obtener el contenido del objeto S3
                S3Object s3Object = s3Client.getObject(fileDto.getBucketName(), fileDto.getObjectKey());
                InputStream imagenGets3 = s3Object.getObjectContent();

                // Comprimir la imagen y crear una miniatura
                ArrayList<Object> objectArrayList = FileCompress.compressAndCreateThumbnail(imagenGets3, logger);// la
                ObjectMetadata md = s3Object.getObjectMetadata();
                int size = (int) objectArrayList.get(1);

                md.setContentLength(size);
                // Subir la imagen comprimida y la miniatura a S3
                InputStream imagenCompress = (InputStream) objectArrayList.get(0);
                uploadToS3(putBucket, fileDto.getObjectKey(), imagenCompress, md);

                logger.log("subido con exito ");
            }
        } catch (Exception e) {
            logger.log("================= MIRAR EL ERROR ==============  \n" + e.getMessage());
        }

        return "subido con exito";
    }

    private void uploadToS3(String bucketName, String objectKey, InputStream inputStream,
            ObjectMetadata objectMetadata) {
        s3Client.putObject(bucketName, objectKey, inputStream, objectMetadata);
    }

}
