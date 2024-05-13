package cn.moexc.vcs.infrasture.oss.minio;

import cn.moexc.vcs.infrasture.config.MinIOConfig;
import cn.moexc.vcs.infrasture.oss.ObjectStorageService;
import io.minio.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import java.io.*;

@Component
@ConditionalOnProperty(prefix = "oss", value = "type", havingValue = "minio")
public class MinioOSS implements ObjectStorageService {

    private final MinioClient minioClient;
    private final String bucketName;
    private final String urlPrefix;

    public MinioOSS(MinioClient minioClient, MinIOConfig minIOConfig) {
        this.minioClient = minioClient;
        this.bucketName = minIOConfig.getBucketName();
        this.urlPrefix = minIOConfig.getMinioServer() + "/" + bucketName + "/";
    }

    @Override
    public String write(String name, InputStream inputStream) throws Exception{
        if (!bucketExists()) makeBucket();
        minioClient.putObject(bucketName, name, inputStream, new PutObjectOptions(inputStream.available(), -1));
        return getUrl(name);
    }

    @Override
    public String getUrl(String name) {
        return urlPrefix + name;
    }

    @Override
    public void read(String name, OutputStream outputStream) throws Exception{
        InputStream response = minioClient.getObject(bucketName, name);
        int len;
        byte[] b = new byte[10240];
        while((len = response.read(b))!=-1){
            outputStream.write(b, 0, len);
        }
    }

    @Override
    public byte[] read(String name) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            this.read(bucketName, outputStream);
            return outputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new byte[0];
    }

    private boolean bucketExists() throws Exception {
        return minioClient.bucketExists(bucketName);
    }

    private void makeBucket() throws Exception{
        minioClient.makeBucket(bucketName);
    }
}
