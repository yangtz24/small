package com.ytz.mall.file.controller;

import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.file.bean.FastDFSFile;
import com.ytz.mall.file.util.FastDFSUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
@RestController
@RequestMapping("file")
public class FileController {

    /**
     * 返回 图片的全路径
     *
     * @param file 页面的文件对象
     * @return
     */
    @PostMapping("/upload")
    public Result<String[]> upload(@RequestParam(value = "file") MultipartFile file) throws Exception {

        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename()));

        String[] uploads = FastDFSUtil.upload(fastDFSFile);

        assert uploads != null;
        String url = FastDFSUtil.getTrackerUrl() + "/" + uploads[0] + "/" + uploads[1];
        if (uploads.length > 0) {
            return new Result<>(true, StatusCode.SUCCESS, "上传成功", url);
        }
        return new Result<>(false, StatusCode.ERROR, "上传失败");

    }
}
