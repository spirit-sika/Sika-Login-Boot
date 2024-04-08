package cc.sika.sikaloginserver;

import cc.sika.sikaloginserver.entity.SikaPermission;
import cc.sika.sikaloginserver.entity.SikaRole;
import cc.sika.sikaloginserver.entity.SikaUser;
import cc.sika.sikaloginserver.mapper.SikaPermissionMapper;
import cc.sika.sikaloginserver.mapper.SikaRoleMapper;
import cc.sika.sikaloginserver.mapper.SikaUserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MPTest {
    @Resource
    private SikaUserMapper sikaUserMapper;
    @Resource
    private SikaRoleMapper sikaRoleMapper;
    @Resource
    private SikaPermissionMapper sikaPermissionMapper;

    @Test
    void testSelectUser() {
        System.out.println("----- start selectAllUser method test ------");
        List<SikaUser> sikaUserList = sikaUserMapper.selectList(null);
        sikaUserList.forEach(System.out::println);
        System.out.println("----- done selectAllUser method test ------");
    }

    @Test
    void testSelectRole() {
        System.out.println("----- start selectAllRole method test ------");
        List<SikaRole> sikaUserList = sikaRoleMapper.selectList(null);
        sikaUserList.forEach(System.out::println);
        System.out.println("----- done selectAllRole method test ------");
    }

    @Test
    void testSelectPermission() {
        System.out.println("----- start selectAllPermission method test ------");
        List<SikaPermission> sikaUserList = sikaPermissionMapper.selectList(null);
        sikaUserList.forEach(System.out::println);
        System.out.println("----- done selectAllPermission method test ------");
    }
}
