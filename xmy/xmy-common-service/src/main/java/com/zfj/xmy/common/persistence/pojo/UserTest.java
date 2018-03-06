package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class UserTest implements Serializable {

    /**
     * user_test.id
     * @ibatorgenerated 2018-01-04 02:31:08
     */
    @Column(name = "id")
    private Long id;


    /**
     * user_test.qq
     * @ibatorgenerated 2018-01-04 02:31:08
     */
    @Column(name = "qq")
    private String qq;


    /**
     * user_test.uuid
     * @ibatorgenerated 2018-01-04 02:31:08
     */
    @Column(name = "uuid")
    private String uuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}