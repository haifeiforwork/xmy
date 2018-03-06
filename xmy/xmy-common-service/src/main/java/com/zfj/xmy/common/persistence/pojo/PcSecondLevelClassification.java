package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class PcSecondLevelClassification implements Serializable {

    /**
     * pc_second_level_classification.id
     * @ibatorgenerated 2017-08-31 20:11:41
     */
    @Column(name = "id")
    private Long id;


    /**
     * pc_second_level_classification.category_id (二级分类id)
     * @ibatorgenerated 2017-08-31 20:11:41
     */
    @Column(name = "category_id")
    private Long categoryId;


    /**
     * pc_second_level_classification.category_name (二级分类名称)
     * @ibatorgenerated 2017-08-31 20:11:41
     */
    @Column(name = "category_name")
    private String categoryName;


    /**
     * pc_second_level_classification.config_id (配置表的id)
     * @ibatorgenerated 2017-08-31 20:11:41
     */
    @Column(name = "config_id")
    private Long configId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }
}