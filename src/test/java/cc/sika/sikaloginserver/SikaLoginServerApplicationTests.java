package cc.sika.sikaloginserver;

import cc.sika.sikaloginserver.entity.SikaUser;
import cc.sika.sikaloginserver.mapper.SikaUserMapper;
import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SikaLoginServerApplicationTests {

    @Test
    void generatePassword() {
        String password = SaSecureUtil.sha256("123456");
        System.out.println(password);
    }

    @Test
    void contextLoads() {
    }

}
