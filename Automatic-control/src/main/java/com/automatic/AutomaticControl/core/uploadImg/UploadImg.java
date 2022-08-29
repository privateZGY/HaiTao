package com.automatic.AutomaticControl.core.uploadImg;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Jisoo
 */
public class UploadImg {
    public Map<String, String> files (MultipartFile[] files, HttpServletRequest request) throws IOException {
        // 实例化字符拼接
        StringBuilder urls1 = new StringBuilder();
        StringBuilder urls2 = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        // 由于是多张图片上传，所以需要用for循环遍历这一个上传的集合
        for (MultipartFile file : files) {
            //获取文件名 : file.getOriginalFilename();
            String uploadFileName = file.getOriginalFilename();
            //如果文件名为空，则让他跳到下一次循环！"字符串"
            if ("".equals(uploadFileName)) {
                continue;
            }
            //上传路径保存设置,设置保存路径到根目录下的upload的目录里面
            String path = request.getServletContext().getRealPath("/upload");
            //如果路径不存在，则创建一个
            File realPath = new File(path);
            // exists()函数是Java中File类的一部分。此函数确定是否存在以抽象文件名表示的文件或目录。如果抽象文件路径存在，则该函数返回true，否则返回false
            if (!realPath.exists()) {
                realPath.mkdir();
            }
            // 注意文件名， UUID用的UUID做文件名，因为文件名可能会出现重复,使用UUID这个类来生成一个随机的UUID
            String filename = UUID.randomUUID().toString();
            // 生成之后把这个文件的后缀给获取出来, 通过(".")里面这个点的位置
            String suff = uploadFileName.substring(uploadFileName.lastIndexOf("."));
            // filename文件名等于生成的UUID,加suff这个后缀;
            filename = filename + suff;
            InputStream is = file.getInputStream(); //获取文件的输入流
            // realPath是当这个路径不存在时创建的路径例如/upload, filename就是上传的文件名比如: UUID.png
            OutputStream os = new FileOutputStream(new File(realPath, filename)); //创建文件输出流
            //读取写出
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
                os.flush();
            }
            os.close();
            is.close();
            // append添加路径,是upload目录下加上/filename文件名,再加上.号分割，多个图片就需要,号分割
            // urls.append("/upload" + "/" + filename + ",");
            urls1.append("/upload" + "/" + filename + ",");
            urls2.append(uploadFileName + ",");

        }
        // 如果这个urls他的长度大于0的时候我们才把他设置进去
        if (urls1.length() > 0) {
            // 设置进javabean里面,
            map.put("urls1", urls1.toString().substring(0, urls1.toString().length() - 1));
            map.put("urls2", urls2.toString().substring(0, urls2.toString().length() - 1));
            return map;
        }
        // 否则返回失败
        return null;
    }

}
