package cc.sika.sikaloginserver.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cc.sika.sikaloginserver.mapper")
public class MPConfig {
}
