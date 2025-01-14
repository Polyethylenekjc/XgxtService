package com.api.xgxt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Img")

public class ImgEntity {
    @Id
	private Long id;
    @Column(name="img",nullable=false,length=512)
    private String imgPath;
}
