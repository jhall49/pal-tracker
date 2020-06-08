package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {

    private String port;
    private String memoryLimit;
    private String cfIinstanceIndex;
    private String cfInstanceAddr;

    public EnvController(@Value("${PORT:NOT SET}") String port,
                         @Value("${MEMORY_LIMIT:NOT SET}") String memoryLimit,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}") String cfInstIndex,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfInstAddr) {
        this.port=port;
        this.memoryLimit=memoryLimit;
        this.cfIinstanceIndex=cfInstIndex;
        this.cfInstanceAddr=cfInstAddr;
    }
    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String,String> env = new HashMap<String,String>();
        env.put("PORT",this.port);
        env.put("MEMORY_LIMIT",this.memoryLimit);
        env.put("CF_INSTANCE_INDEX",this.cfIinstanceIndex);
        env.put("CF_INSTANCE_ADDR",this.cfInstanceAddr);
        return env;
    }
}
