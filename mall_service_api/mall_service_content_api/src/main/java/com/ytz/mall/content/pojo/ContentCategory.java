package com.ytz.mall.content.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: ContentCategory
 * @Description: TODO 分类
 * @author: yangtz
 * @date: 2020/10/20
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_content_category")
public class ContentCategory implements Serializable {
    private static final long serialVersionUID = 2984508479853087901L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * //分类名称
     */
    @Column(name = "name")
    private String name;
}
