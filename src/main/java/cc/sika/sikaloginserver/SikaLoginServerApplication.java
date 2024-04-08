package cc.sika.sikaloginserver;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SikaLoginServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SikaLoginServerApplication.class, args);
        System.out.println("sika-login服务器启动成功, sa-token配置如下: " + SaManager.getConfig());
    }

}
