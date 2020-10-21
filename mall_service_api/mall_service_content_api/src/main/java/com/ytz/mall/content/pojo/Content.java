package com.ytz.mall.content.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: Content
 * @Description: TODO 广告内容
 * @author: yangtz
 * @date: 2020/10/20
 * @Version: V1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_content")
public class Content implements Serializable {
    private static final long serialVersionUID = 931451351954245004L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * //内容类目ID
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * //内容标题
     */
    @Column(name = "title")
    private String title;

    /**
     * //链接
     */
    @Column(name = "url")
    private String url;

    /**
     * //图片绝对路径
     */
    @Column(name = "pic")
    private String pic;

    /**
     * //状态,0无效，1有效
     */
    @Column(name = "status")
    private String status;

    /**
     * //排序
     */
    @Column(name = "sort_order")
    private Integer sortOrder;
}
