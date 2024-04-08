package cc.sika.sikaloginserver.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sika_role")
public class SikaRole {
    @TableId(type = IdType.AUTO)
    private Long roleId;
    private String roleName;
    private String roleDescription;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
