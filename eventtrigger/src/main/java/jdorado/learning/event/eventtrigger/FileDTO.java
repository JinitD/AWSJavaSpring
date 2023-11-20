package jdorado.learning.event.eventtrigger;

import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;

public class FileDTO {
    @Override
    public String toString() {
        return "{ FileDTO \n [ bucketName = " + bucketName + ", objectKey = " + objectKey + ", size = " + size + " ] }";
    }

    private String bucketName;
    private String objectKey;
    private Long size;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public FileDTO(S3EventNotificationRecord record) {
        this.bucketName = record.getS3().getBucket().getName();
        this.objectKey = record.getS3().getObject().getKey();
        this.size = record.getS3().getObject().getSizeAsLong();
    }

}
