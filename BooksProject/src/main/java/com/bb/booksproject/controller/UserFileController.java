package com.bb.booksproject.controller;



import com.bb.booksproject.pojo.UserFile;
import com.bb.booksproject.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("UserFile")
public class UserFileController {
    @Autowired
    private HttpServletRequest request;


    @Autowired
    private UserFileService userFileService;


    @RequestMapping("/list")
    public String list() {
        List<UserFile> proList = this.userFileService.queryAll();
        request.setAttribute("list", proList);
        return "listFile";
    }

    @RequestMapping("/addview")
    public String addview() {
        return "addFile";
    }

    @RequestMapping("/add")
    public String add(@RequestParam("file") MultipartFile[] file, UserFile userFile) {
        // 判断文件是否为空，空则返回失败页面
        if (null == file || file.length <= 0) {
            request.setAttribute("message", "1");
            return "addFile";
        }
        // 获取文件存储路径（绝对路径）
        String path = request.getServletContext().getRealPath("/uploadimg");
        // 获取原文件名
        for (int i = 0; i < file.length; i++) {
            MultipartFile m = file[i];
            String fileName = UUID.randomUUID() + m.getOriginalFilename();
            // 创建文件实例
            File filePath = new File(path, fileName);
            // 如果文件目录不存在，创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录" + filePath);
            }
            System.out.println(path);
            // 写入文件
            try {
                m.transferTo(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (i == 0) {
                userFile.setImgsrc(fileName);
            } else {
                userFile.setTxtsrc(fileName);
            }
        }
        userFile.setTimes(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        this.userFileService.add(userFile);
        request.setAttribute("message", "2");
        return "addFile";


    }

    @RequestMapping("/del")
    public String del(String id) {
        this.userFileService.del(id);
        return "forward:list.do";
    }

    @RequestMapping("/toupdate")
    public String toupdate(String id) {
        UserFile userFile = this.userFileService.selectById(id);
        request.setAttribute("v", userFile);
        return "updateFile";
    }

    @RequestMapping("/showtxt")
    public String showtxt(String id) {
        UserFile userFile = this.userFileService.selectById(id);
        String path = request.getServletContext().getRealPath("/uploadimg");
        System.out.println(path);
        String txtsrc = userFile.getTxtsrc();
        File file = new File(path, txtsrc);
        String s = readFile(file.getPath());
        System.out.println(s);
        request.setAttribute("v", userFile);
        request.setAttribute("str", s);
        return "showFile";
    }

    public static String readFile(String fileName) {
        String fileContent = "";
        try {
            File f = new File(fileName);
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(f), "gbk");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent += line;
                }
                read.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent;
    }


    @RequestMapping("/update")
    public String update(@RequestParam("file") MultipartFile[] file, UserFile userFile) {
        String path = request.getServletContext().getRealPath("/uploadimg");
        // 判断文件是否为空，空则返回失败页面   
        for (int i = 0; i < file.length; i++) {
            MultipartFile m = file[i];
            String fileName = UUID.randomUUID() + m.getOriginalFilename();
            // 创建文件实例
            File filePath = new File(path, fileName);
            // 如果文件目录不存在，创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录" + filePath);
            }
            System.out.println(path);
            // 写入文件
            try {
                m.transferTo(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (i == 0) {
                userFile.setImgsrc(fileName);
            } else {
                userFile.setTxtsrc(fileName);
            }
        }
        this.userFileService.update(userFile);
        return "forward:list.do";
    }
}
