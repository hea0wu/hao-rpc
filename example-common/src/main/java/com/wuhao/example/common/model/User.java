package com.wuhao.example.common.model;

import java.io.Serializable;

/**
* @author:WuHao
* @version:1.0
 *
 * 用户类
*/

public class User implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
