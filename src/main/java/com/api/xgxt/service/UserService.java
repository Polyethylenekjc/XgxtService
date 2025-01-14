package com.api.xgxt.service;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.xgxt.db.UserRepository;
import com.api.xgxt.entity.Password;
import com.api.xgxt.entity.UserEntity;
import com.api.xgxt.utils.StatuesCode;

@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public final void createAdmin(Password password) {
        if(!password.password.equals("memberofkjc")){
            return;
        }
        UserEntity user = new UserEntity();
        user.setId(Long.valueOf(-1));
        user.setName("admin");
        user.setPassword("memberofkjc");
        user.setPermission(0);
        userRepository.save(user);
    }

    public final @ResponseBody ResponseEntity<String> getAllUsers() {
        try{
            Iterable<UserEntity> allUser = userRepository.findAll();
            Iterator<UserEntity> iterator = allUser.iterator();
            if(iterator.hasNext()){
                UserEntity temp = iterator.next();
                if(temp.getId() == -1){
                    iterator.remove();
                }
            }
            return StatuesCode.StatuesOkMsg(allUser.toString());
        }
        catch(Exception e){
            return StatuesCode.InternalServerError();
        }
    }

    public final ResponseEntity<String> addNewUser (List<UserEntity> userlist) {
        for (UserEntity user : userlist) {
            try{
                if(userRepository.existsById(user.getId())){
                    return StatuesCode.BadRequest("用户ID已存在,请调用Api updateUser");
                }
                userRepository.save(user);
            }
            catch(org.springframework.orm.jpa.JpaSystemException e){
                return StatuesCode.BadRequest("表单缺少主键:需要主键ID "+e.getMessage());
            }
            catch(org.springframework.dao.DataIntegrityViolationException e){
                return StatuesCode.BadRequest("表单非空键值空缺:请确保所有非空键值不为空 "+e.getMessage());
            }
        }
        return StatuesCode.StatuesOk();
    }

    public final ResponseEntity<String> getUserById (Long id) {
        try{
            return StatuesCode.StatuesOkMsg(userRepository.findById(id).get().toString());
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到用户ID为"+id+"的用户");
        }
    }  
    
    public final ResponseEntity<String> CheckPermission (Long id){
        try{
            return StatuesCode.StatuesOkMsg(String.valueOf(userRepository.findById(id).get().getPermission()));
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到用户ID为"+id+"的用户");
        }
    }

    public final ResponseEntity<String> checkPassword(Long id, String password) {
        try{
            String pass = userRepository.findById(id).get().getPassword();
            if(pass.equals(password)){
                return StatuesCode.StatuesOkMsg("密码正确");
            }
            else{
                return StatuesCode.Unauthorized("密码错误");
            }
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到用户ID为"+id+"的用户");
        }
    }

    public final ResponseEntity<String> updateUser(UserEntity user) {
        try{
            if(!userRepository.existsById(user.getId())){
                return StatuesCode.BadRequest("找不到用户ID为"+user.getId()+"的用户");
            }
            if(user.getPassword() != null){
                return StatuesCode.BadRequest("修改了用户密码,请调用Api updataUserPassword");
            }
            userRepository.save(user);
            return StatuesCode.StatuesOk();
        }
        catch(org.springframework.orm.jpa.JpaSystemException e){
            return StatuesCode.BadRequest("表单缺少主键:需要主键ID "+e.getMessage());
        }
        catch(org.springframework.dao.DataIntegrityViolationException e){
            return StatuesCode.BadRequest("表单非空键值空缺:请确保所有非空键值不为空 "+e.getMessage());
        }
    }

    public final ResponseEntity<String> deleteUser(Long id) {
        try{
            userRepository.deleteById(id);
            return StatuesCode.StatuesOk();
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到用户ID为"+id+"的用户");
        }
    }
    
    public final ResponseEntity<String> updateUserId(Long id, Long uid) {
        try{
            UserEntity user = userRepository.findById(id).get();
            user.setId(uid);
            deleteUser(id);
            userRepository.save(user);
            return StatuesCode.StatuesOk();
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到用户ID为"+id+"的用户"+e.getMessage());
        }
    }

    public final ResponseEntity<String> updateUserPassword(Password newpassword) {
        try{
            UserEntity user = userRepository.findById(newpassword.id).get();
            if(!user.getPassword().equals(newpassword.password)){
                System.out.println("user.getPassword()="+user.getPassword());
                return StatuesCode.Unauthorized("旧密码错误");
            }
            user.setPassword(newpassword.newpassword);
            userRepository.save(user);
            return StatuesCode.StatuesOk();
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到用户ID为"+newpassword.id+"的用户");
        }
    }

    public final ResponseEntity<String> sudoUser(Password sudo, Password u) {
        try{
            String pass = userRepository.findById(sudo.id).get().getPassword();
            if(pass.equals(sudo.password)){
                UserEntity user = userRepository.findById(u.id).get();
                user.setPermission(u.permission);
                userRepository.save(user);
                return StatuesCode.StatuesOk();
            }
            else{
                return StatuesCode.Unauthorized("Sudo用户密码错误");
            }
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到用户ID为"+sudo.id+"的用户或找不到用户ID为"+u.id+"的用户");
        }
    }

    //query by username
    public final ResponseEntity<String> getUserByUsername(String username) {
        try{
            String join = StringUtils.join(userRepository.findByUsername(username), ",");
            return StatuesCode.StatuesOkMsg("["+join+"]");
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到用户名为"+username+"的用户");
        }
    }

    public final ResponseEntity<String> getUserByClasses(String classes) {
        try{
            String join = StringUtils.join(userRepository.findByClasses(classes), ",");
            return StatuesCode.StatuesOkMsg("["+join+"]");
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到班级"+classes);
        }
    }

    public final ResponseEntity<String> getUserByGrade(String grade) {
        try{
            String join = StringUtils.join(userRepository.findByGrade(grade), ",");
            return StatuesCode.StatuesOkMsg("["+join+"]");
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到年级"+grade);
        }   
    }

    public final ResponseEntity<String> getUserByMajor(String major) {
        try{
            String join = StringUtils.join(userRepository.findByMajor(major), ",");
            return StatuesCode.StatuesOkMsg("["+join+"]");
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到专业"+major);
        }   
    }

    public final ResponseEntity<String> getUserBySubject(String subject) {
        try{
            String join = StringUtils.join(userRepository.findBySubject(subject), ",");
            return StatuesCode.StatuesOkMsg("["+join+"]");
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到科目"+subject);
        }   
    }   

    public final ResponseEntity<String> getUserByGradeAndMajor(String grade, String major) {
        try{
            String join = StringUtils.join(userRepository.findByGradeAndMajor(grade, major), ",");
            return StatuesCode.StatuesOkMsg("["+join+"]");
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到年级"+grade+"专业"+major+"的用户");
        }
    }

    public final ResponseEntity<String> getUserByMajorAndSubject(String major, String subject) {
        try{
            String join = StringUtils.join(userRepository.findByMajorAndSubject(major, subject), ",");
            return StatuesCode.StatuesOkMsg("["+join+"]");
        }   
        catch(Exception e){
            return StatuesCode.BadRequest("找不到专业"+major+"科目"+subject+"的用户");
        }   
    }

    public final ResponseEntity<String> getUserByGradeAndSubject(String grade, String subject) {
        try{
            String join = StringUtils.join(userRepository.findByGradeAndSubject(grade, subject), ",");
            return StatuesCode.StatuesOkMsg("["+join+"]");
        }
        catch(Exception e){
            return StatuesCode.BadRequest("找不到年级"+grade+"科目"+subject+"的用户");
        }       
    }
}
