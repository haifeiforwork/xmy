package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class JiguangPushLabel implements Serializable {

    private Long id;


    private String label;


    private Date createTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}