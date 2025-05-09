package com.mlinyun.springboot3demo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    private Integer isDelete;

}
