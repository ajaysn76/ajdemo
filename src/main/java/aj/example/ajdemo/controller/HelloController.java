package aj.example.ajdemo.controller;

import aj.example.ajdemo.service.AmazonClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Controller;

@Controller
@Path("/v1")
public class HelloController {
  private AmazonClient amazonClient;

  HelloController(AmazonClient amazonClient) {
    this.amazonClient = amazonClient;
  }


  @GET
  @Path("/hello-world")
  public String helloWorld() {
    return "Hello World!";
  }

  @POST
  @Path("/upload-file")
  public String uploadFile(@FormDataParam("file") InputStream fileInputStream,
      @FormDataParam("file") FormDataContentDisposition fileMetaData) {
    String fileName = fileMetaData.getFileName();
    String fileLocation = "/tmp" + File.separator + fileName;

    File targetFile = new File(fileLocation);
    try {
      Files.copy(fileInputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      return "File uploaded successfully!";
    }
    amazonClient.uploadFileTos3bucket(fileName, targetFile);
    return "File uploaded successfully!";
  }

}
