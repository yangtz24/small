package com.ytz.mall.user.dao;

import com.ytz.mall.user.pojo.UndoLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName: UndoLogMapper
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/26
 * @Version: V1.0
 */
@Repository
public interface UndoLogMapper extends Mapper<UndoLog> {
}
