package com.ytz.mall.content.dao;

import com.ytz.mall.content.pojo.Content;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName: ContentMapper
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/20
 * @Version: V1.0
 */
@Repository
public interface ContentMapper extends Mapper<Content> {
}
