package cn.itsource.hrm.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.util.FastDfsApiOpr;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description TODO
 * @Author hjz
 * @Date 2019/12/30 9:50
 * @Version v1.0
 **/
@Api(tags = "文件上传的接口类")
@RestController
@RequestMapping("/fastdfs")
public class FileController {

    /**
     * 文件上传
     * springmvc 实现文件上传
     * @return
     */
    @ApiOperation(value = "文件上传",notes = "fastdfs的文件上传")
    @PostMapping("/upload")
    public AjaxResult file_upload(MultipartFile file){

        String fileName = file.getName();
        String originalFilename = file.getOriginalFilename();

//        System.out.println("fileName="+fileName);// file
//        System.out.println("originalFilename="+originalFilename);//5.jpg

        //文件后缀名
        int index = originalFilename.lastIndexOf(".");
        String extName = originalFilename.substring(index + 1);
        try {
            String file_id = FastDfsApiOpr.upload(file.getBytes(), extName);
            return AjaxResult.me().setResultObj(file_id);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败!"+e.getMessage());
        }
    }

    /**
     * 文件删除
     * @param file_id  /group1/M00/00/01/rBAFZF4HmpuASg0LAAVJ-pk7-do81.jpeg
     * @return
     */
    @ApiOperation(value = "文件删除",notes = "fastdfs的文件删除")
    @ApiImplicitParam(name = "file_id",required = true,dataType = "string",paramType = "query")
    @GetMapping("/delete")
    public AjaxResult delete(String file_id){
        // file_id = /group1/M00/00/01/rBAFZF4HmpuASg0LAAVJ-pk7-do81.jpeg
        try {
            // file_id =  group1/M00/00/01/rBAFZF4HmpuASg0LAAVJ-pk7-do81.jpeg
            file_id = file_id.substring(1);//去掉第一个  /
            int index = file_id.indexOf("/");
            String groupName = file_id.substring(0,index);
            String fileName = file_id.substring(index+1);
            FastDfsApiOpr.delete(groupName,fileName);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败!"+e.getMessage());
        }

    }


}
