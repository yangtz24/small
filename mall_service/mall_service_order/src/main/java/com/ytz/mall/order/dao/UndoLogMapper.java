package com.ytz.mall.order.dao;
import com.ytz.mall.order.pojo.UndoLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


/**
 * @author yangt
 */
@Repository
public interface UndoLogMapper extends Mapper<UndoLog> {
}
