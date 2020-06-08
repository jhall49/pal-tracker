package io.pivotal.pal.tracker;

import java.util.logging.Logger;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class WelcomeController {
    String CLASS_NAME=WelcomeController.class.getName();
    Logger LOGGER = Logger.getLogger(CLASS_NAME);

    private String wellcomMessage;

    public String getWellcomMessage() {
        return this.wellcomMessage;
    }

    public void setWellcomMessage(String wellcomMessage) {
        this.wellcomMessage = wellcomMessage;
    }


    public WelcomeController(@Value("${welcome_message}") String a_welcome_message ) {
        this.wellcomMessage = a_welcome_message;

    }

    @GetMapping("/")
    public String sayHello(){
        LOGGER.logp(Level.INFO,CLASS_NAME,"sayHello","\n"+this.wellcomMessage+"\n");
        return this.wellcomMessage ;
    }
}
