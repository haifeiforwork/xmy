package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class GoodsSpecCategory implements Serializable {

    /**
     * goods_spec_category.id
     * @ibatorgenerated 2017-08-08 16:40:34
     */
    @Column(name = "id")
    private Long id;


    /**
     * goods_spec_category.goods_id (商品id)
     * @ibatorgenerated 2017-08-08 16:40:34
     */
    @Column(name = "goods_id")
    private Long goodsId;


    /**
     * goods_spec_category.spec_category_id (规格分类id)
     * @ibatorgenerated 2017-08-08 16:40:34
     */
    @Column(name = "spec_category_id")
    private Long specCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecCategoryId() {
        return specCategoryId;
    }

    public void setSpecCategoryId(Long specCategoryId) {
        this.specCategoryId = specCategoryId;
    }
}