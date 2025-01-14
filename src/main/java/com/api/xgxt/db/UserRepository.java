package com.api.xgxt.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.xgxt.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    //findByUsername
    @Query(value = "select * from user where name like ?1", nativeQuery = true)
    public List<UserEntity> findByUsername(String username);

    @Query(value = "select * from user where classes like ?1", nativeQuery = true)
    public List<UserEntity> findByClasses(String classes);

    //findbygrade
    @Query(value = "select * from user where grade like ?1", nativeQuery = true)
    public List<UserEntity> findByGrade(String grade);

    //findbymajor
    @Query(value = "select * from user where major like ?1", nativeQuery = true)
    public List<UserEntity> findByMajor(String major);

    //findbysubject
    @Query(value = "select * from user where subject like ?1", nativeQuery = true)
    public List<UserEntity> findBySubject(String subject);

    @Query(value = "select * from user where grade like ?1 and major like ?2", nativeQuery = true)
    public List<UserEntity> findByGradeAndMajor(String grade, String major);

    @Query(value = "select * from user where grade like ?1 and subject like ?2", nativeQuery = true)
    public List<UserEntity> findByGradeAndSubject(String grade, String subject);

    @Query(value = "select * from user where major like ?1 and subject like ?2", nativeQuery = true)
    public List<UserEntity> findByMajorAndSubject(String major, String subject);

}
