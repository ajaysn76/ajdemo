package aj.example.ajdemo.config;

import aj.example.ajdemo.controller.HelloController;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    register(HelloController.class);
    register(MultiPartFeature.class);
  }

}
