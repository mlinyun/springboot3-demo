package com.mlinyun.springboot3demo.entity.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDeleteRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户 id
     */
    private Integer id;

}
