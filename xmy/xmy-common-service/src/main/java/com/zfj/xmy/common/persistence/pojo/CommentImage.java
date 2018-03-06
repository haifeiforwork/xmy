package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class CommentImage implements Serializable {

    /**
     * comment_image.id
     * @ibatorgenerated 2017-07-21 16:04:07
     */
    @Column(name = "id")
    private Long id;


    /**
     * comment_image.comment_id (评论id)
     * @ibatorgenerated 2017-07-21 16:04:07
     */
    @Column(name = "comment_id")
    private Long commentId;


    /**
     * comment_image.image_path (图片路径)
     * @ibatorgenerated 2017-07-21 16:04:07
     */
    @Column(name = "image_path")
    private String imagePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}