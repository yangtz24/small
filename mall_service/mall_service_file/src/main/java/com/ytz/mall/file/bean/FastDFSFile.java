package com.ytz.mall.file.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: FastDFSFile
 * @Description: TODO  文件信息
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastDFSFile implements Serializable {

    private static final long serialVersionUID = -3926331151614702019L;
    //文件名字
    private String name;
    //文件内容
    private byte[] content;
    //文件扩展名
    private String ext;
    //文件MD5摘要值
    private String md5;
    //文件创建作者
    private String author;

    public FastDFSFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
}
