package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class CategoryWordSeg implements Serializable {

    /**
     * category_word_seg.id
     * @ibatorgenerated 2017-08-09 10:45:46
     */
    @Column(name = "id")
    private Long id;


    /**
     * category_word_seg.cid (最后一级分类id)
     * @ibatorgenerated 2017-08-09 10:45:46
     */
    @Column(name = "cid")
    private Long cid;


    /**
     * category_word_seg.word_seg (分词名称)
     * @ibatorgenerated 2017-08-09 10:45:46
     */
    @Column(name = "word_seg")
    private String wordSeg;


    /**
     * category_word_seg.img_path (图片路径)
     * @ibatorgenerated 2017-08-09 10:45:46
     */
    @Column(name = "img_path")
    private String imgPath;


    /**
     * category_word_seg.seq (排序)
     * @ibatorgenerated 2017-08-09 10:45:46
     */
    @Column(name = "seq")
    private Integer seq;


    /**
     * category_word_seg.create_time (创建时间)
     * @ibatorgenerated 2017-08-09 10:45:46
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * category_word_seg.parent_id (父级id)
     * @ibatorgenerated 2017-08-09 10:45:46
     */
    @Column(name = "parent_id")
    private Long parentId;


    /**
     * category_word_seg.is_show
     * @ibatorgenerated 2017-08-09 10:45:46
     */
    @Column(name = "is_show")
    private Integer isShow;


    /**
     * category_word_seg.is_search (是否搜索)
     * @ibatorgenerated 2017-08-09 10:45:46
     */
    @Column(name = "is_search")
    private Integer isSearch;


    /**
     * category_word_seg.type (类型 0：首页菜单 1：分类规格)
     * @ibatorgenerated 2017-08-09 10:45:46
     */
    @Column(name = "type")
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getWordSeg() {
        return wordSeg;
    }

    public void setWordSeg(String wordSeg) {
        this.wordSeg = wordSeg;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(Integer isSearch) {
        this.isSearch = isSearch;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}