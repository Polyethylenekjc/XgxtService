package com.api.xgxt.service;

import java.io.File;
import com.api.xgxt.utils.StatuesCode;
import com.api.xgxt.db.ImgRepository;
import com.api.xgxt.entity.ImgEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("imgService")
public class ImgService {
    @Autowired
    private ImgRepository imgRepository;

    public final ResponseEntity<String> uploadImg(Long id, MultipartFile file) {
        String path = "image/" + id + ".png";
        String abolutePath = System.getProperty("user.dir") + "/" + "img/" + id + ".png";
        ImgEntity imgEntity = new ImgEntity();
        imgEntity.setId(id);
        imgEntity.setImgPath(path);
        imgRepository.save(imgEntity);
        File img = new File(abolutePath);
        File dircheck = new File(System.getProperty("user.dir") + "/img");
        if(!dircheck.isDirectory()){
            dircheck.mkdir();
            System.out.println("文件夹创建成功");
        }
        try {
            file.transferTo(img);
        } catch (Exception e) {
            e.printStackTrace();
            return StatuesCode.BadRequest(e.getMessage());
        }
        return StatuesCode.StatuesOkMsg("image/" + id + ".png");
    }

    public final ResponseEntity<String> getImgById(Long id) {
        try{
            return StatuesCode.StatuesOkMsg(imgRepository.findById(id).get().getImgPath());
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到图片ID为"+id+"的图片");
        }
    }
}   