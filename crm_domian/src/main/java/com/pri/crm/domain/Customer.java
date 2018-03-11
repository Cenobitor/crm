package com.pri.crm.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 8:13 PM 08/02/2018
 * @Modified By:
 */
@Entity
@Table(name = "cst_customer")
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cust_id;

    @Basic
    @Column
    private String cust_name;
    @Basic
    @Column
    private String cust_phone;
    @Basic
    @Column
    private String cust_address;
    @Basic
    @Column
    private String cust_image;

    @ManyToOne
    @JoinColumn(name = "cust_industry",referencedColumnName = "dict_id")
    private BaseDict cust_industry;
    @ManyToOne
    @JoinColumn(name = "cust_level",referencedColumnName = "dict_id")
    private BaseDict cust_level;
    @ManyToOne
    @JoinColumn(name = "cust_source",referencedColumnName = "dict_id")
    private BaseDict cust_source;

    @ManyToOne
    @JoinColumn(name = "cust_user_id",referencedColumnName = "user_id")
    private User cust_user_id;  //谁去负责这个客户
    @ManyToOne
    @JoinColumn(name = "cust_create_id",referencedColumnName = "user_id")
    private User cust_create_id; //谁创建了这个客户

    @OneToMany(mappedBy = "customer")
    private Set<LinkMan> linkMans = new HashSet<>();

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    public String getCust_image() {
        return cust_image;
    }

    public void setCust_image(String cust_image) {
        this.cust_image = cust_image;
    }

    public Long getCust_id() {
        return cust_id;
    }

    public void setCust_id(Long cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public BaseDict getCust_industry() {
        return cust_industry;
    }

    public void setCust_industry(BaseDict cust_industry) {
        this.cust_industry = cust_industry;
    }

    public BaseDict getCust_level() {
        return cust_level;
    }

    public void setCust_level(BaseDict cust_level) {
        this.cust_level = cust_level;
    }

    public BaseDict getCust_source() {
        return cust_source;
    }

    public void setCust_source(BaseDict cust_source) {
        this.cust_source = cust_source;
    }

    public User getCust_user_id() {
        return cust_user_id;
    }

    public void setCust_user_id(User cust_user_id) {
        this.cust_user_id = cust_user_id;
    }

    public User getCust_create_id() {
        return cust_create_id;
    }

    public void setCust_create_id(User cust_create_id) {
        this.cust_create_id = cust_create_id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cust_id=" + cust_id +
                ", cust_name='" + cust_name + '\'' +
                ", cust_phone='" + cust_phone + '\'' +
                ", cust_address='" + cust_address + '\'' +
                ", cust_industry=" + cust_industry +
                ", cust_level=" + cust_level +
                ", cust_source=" + cust_source +
                ", cust_user_id=" + cust_user_id +
                ", cust_create_id=" + cust_create_id +
                ", cust_image='" + cust_image + '\'' +
                ", linkMans=" + linkMans +
                '}';
    }
}
