package cc.sika.sikaloginserver.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sika_user")
public class SikaUser {
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    /* 性别 0:未知 1:男 2:女 */
    private String sex;
    /* 0:禁用, 1:正常 */
    private int status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
