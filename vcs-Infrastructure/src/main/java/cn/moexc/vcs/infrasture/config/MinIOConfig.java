package cn.moexc.vcs.infrasture.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConditionalOnProperty(prefix = "oss", value = "type", havingValue = "minio")
public class MinIOConfig {

    @Value("${minio.server}")
    private String minioServer;

    @Value("${minio.accesskey}")
    private String accesskey;

    @Value("${minio.secretkey}")
    private String secretkey;

    @Value("${minio.bucket}")
    private String bucketName;

    @Bean
    public MinioClient buildMinioClient() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(minioServer, accesskey, secretkey);
    }
}
