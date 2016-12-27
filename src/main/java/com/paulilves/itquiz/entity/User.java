//package com.paulilves.itquiz.entity;
//
//import org.hibernate.annotations.DynamicUpdate;
//import org.hibernate.annotations.Type;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@DynamicUpdate
//@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
//public class User {
//    private String username;
//    private List<Authority> authorities;
//    @Type(type = "numeric_boolean")
//    private boolean enabled;
//
//
//    @Id
//    @Column(name = "username")
//    public String getUsername() {
//        return username;
//    }
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "authorities",
//            joinColumns = @JoinColumn(name = "username"),
//            inverseJoinColumns = @JoinColumn(name = "rolename")
//    )
//    public List<Authority> getauthorities() {
//        return authorities;
//    }
//
//    @Column(name = "ENABLED")
//    public boolean isEnabled() {
//        return enabled;
//    }
//}