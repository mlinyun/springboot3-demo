package com.mlinyun.springboot3demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@TableName(value = "tb_user")
@Schema(name = "用户实体(TbUser)", description = "用户信息表实体类")
public class TbUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "用户ID", example = "1")
    private Integer id;

    /**
     * 用户名
     */
    @Schema(description = "用户名", example = "LingYun", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码", example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    /**
     * 是否删除
     */
    @TableLogic // 逻辑删除注解
    @Schema(description = "是否删除(0-未删除,1-已删除)", example = "0")
    private Integer isDelete;

}