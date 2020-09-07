package com.forkhome.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RequestMapping("/resumeHome")
@RestController
@CrossOrigin
public class resumeHome {

    @GetMapping("/getResume")
    public void getResume(HttpServletRequest request , HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {e1.printStackTrace();}

        String filePath= "static/resume_tianyujin.docx";

        //File file = new File(ClassLoader.getSystemResource(filePath).getFile());
        //System.out.println(file);           //没问题
        // 以流的形式下载文件
        BufferedInputStream fis = null;
        BufferedOutputStream toClient = null;
        try {
            long fileLength = new File(filePath).length();
            fis = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(filePath));
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition", "attachment;filename=resume_tianyujin.docx");
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = fis.read(buff, 0, buff.length))) {
                toClient.write(buff, 0, bytesRead);
            }
            response.addHeader("Content-Length", "" + buff.length);

        }catch (Exception e){
            System.out.println("出错了？！");
            e.printStackTrace();
        }finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (toClient != null){
                try {
                    toClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("结束");
    }
}
