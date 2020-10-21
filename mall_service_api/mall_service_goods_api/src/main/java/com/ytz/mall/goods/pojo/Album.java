package com.ytz.mall.goods.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Table(name="tb_album")
@Data
public class Album implements Serializable{

	private static final long serialVersionUID = -7904589659302725009L;
	/**
	 * 	编号
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	/**
	 * 	相册名称
	 */
    @Column(name = "title")
	private String title;

	/**
	 * 	相册封面
	 */
    @Column(name = "image")
	private String image;

	/**
	 * 	图片列表
	 */
    @Column(name = "image_items")
	private String imageItems;


}
