package com.api.xgxt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="User")
public class UserEntity {
	@Id
	private Long id;

    @Column(name="password",nullable=true)
    private String password;

    @Column(name="permission",nullable=true)
    private int permission;

    @Column(name="name",length=50,nullable=true)
	private String name;

    @Column(name="classes",length=50,nullable=true)
	private String classes;

    @Column(name="grade",length=20,nullable=true)
    private String grade;

    @Column(name="major",length=20,nullable=true)
    private String major;

    @Column(name="subject",length=20,nullable=true)
    private String subject;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", password=" + password +
                ", permission=" + permission +
                ", name='" + name + '\'' +
                ", classes='" + classes + '\'' +
                ", grade='" + grade + '\'' +
                ", major='" + major + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    
}