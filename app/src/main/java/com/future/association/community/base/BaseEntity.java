package com.future.association.community.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class BaseEntity implements Serializable{
    public String id ;

    public BaseEntity(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
