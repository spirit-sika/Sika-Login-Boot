package cc.sika.sikaloginserver.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sika_permission")
public class SikaPermission {
    @TableId(type = IdType.AUTO)
    private Long permissionId;
    private String permissionContent;
    private String permissionDescription;
    private String permissionType;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
