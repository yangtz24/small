package com.ytz.mall.order.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author yangt
 */
@Table(name="undo_log")
@Data
public class UndoLog implements Serializable{

	private static final long serialVersionUID = 8075918412044156010L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

    @Column(name = "branch_id")
	private Long branchId;

    @Column(name = "xid")
	private String xid;

    @Column(name = "rollback_info")
	private String rollbackInfo;

    @Column(name = "log_status")
	private Integer logStatus;

    @Column(name = "log_created")
	private Date logCreated;

    @Column(name = "log_modified")
	private Date logModified;

    @Column(name = "ext")
	private String ext;


}
