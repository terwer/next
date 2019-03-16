package com.terwergreen.jvueserver.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Post
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/16 15:32
 **/
@Getter
@Setter
public class Post {
    /**
     * 文章ID
     */
    private Integer id;
    /**
     * 内容标题
     */
    private String title;

    /**
     * 内容生成时间
     */
    private Date created;

    /**
     * 内容修改时间
     */
    private Date modified;

    /**
     * 简介
     */
    private String desc;

    /**
     * html内容
     */
    private String content;

    /**
     * 原始内容
     */
    private String rawContent;

    /**
     * 内容所属用户id
     */
    private Integer authorId;

    /**
     * 点击量
     */
    private Integer hits;

    /**
     * 标签列表
     */
    private String tags;

    /**
     * 文章分类
     */
    private String category;

    /**
     * 内容状态
     */
    private String status;

    /**
     * 内容类别
     */
    private String type;

    /**
     * 是否允许评论
     */
    private Boolean allowComment;

    /**
     * 评论数量
     */
    private Integer commentCount;
}
