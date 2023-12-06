package aj.example.ajdemo.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.annotation.PostConstruct;
import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AmazonClient {

  private AmazonS3 s3client;

  @Value("${amazonProperties.endpointUrl}")
  private String endpointUrl;
  @Value("${amazonProperties.bucketName}")
  private String bucketName;
  @Value("${amazonProperties.accessKey}")
  private String accessKey;
  @Value("${amazonProperties.secretKey}")
  private String secretKey;

  public String  uploadFileTos3bucket(String fileName, File file) {
    String fileUrl = "";
    fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
    s3client.putObject(new PutObjectRequest(bucketName, "ajpracticefolder/"+fileName, file)
        .withCannedAcl(CannedAccessControlList.PublicRead));
    return fileUrl;
  }

  @PostConstruct
  private void initializeAmazon() {
    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
    this.s3client = new AmazonS3Client(credentials);
  }
}