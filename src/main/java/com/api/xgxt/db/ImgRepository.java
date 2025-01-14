package com.api.xgxt.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.xgxt.entity.ImgEntity;

public interface ImgRepository extends CrudRepository<ImgEntity, Long> {
    @Query(value = "select * from img", nativeQuery = true)
    public List<ImgEntity> findAllImg();
}