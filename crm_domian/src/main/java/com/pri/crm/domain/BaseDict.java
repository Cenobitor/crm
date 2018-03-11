package com.pri.crm.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 8:20 PM 08/02/2018
 * @Modified By:
 */
@Entity
@Table(name = "base_dict")
public class BaseDict {
    @Id
    @Column
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String dict_id;
    @Basic
    @Column
    private String dict_type_code;
    @Basic
    @Column
    private String dict_type_name;
    @Basic
    @Column
    private String dict_item_name;
    @Basic
    @Column
    private String dict_item_code;
    @Basic
    @Column
    private String dict_sort;
    @Basic
    @Column
    private char dict_enable;
    @Basic
    @Column
    private String dict_memo;

    public String getDict_id() {
        return dict_id;
    }

    public void setDict_id(String dict_id) {
        this.dict_id = dict_id;
    }

    public String getDict_type_code() {
        return dict_type_code;
    }

    public void setDict_type_code(String dict_type_code) {
        this.dict_type_code = dict_type_code;
    }

    public String getDict_type_name() {
        return dict_type_name;
    }

    public void setDict_type_name(String dict_type_name) {
        this.dict_type_name = dict_type_name;
    }

    public String getDict_item_name() {
        return dict_item_name;
    }

    public void setDict_item_name(String dict_item_name) {
        this.dict_item_name = dict_item_name;
    }

    public String getDict_item_code() {
        return dict_item_code;
    }

    public void setDict_item_code(String dict_item_code) {
        this.dict_item_code = dict_item_code;
    }

    public String getDict_sort() {
        return dict_sort;
    }

    public void setDict_sort(String dict_sort) {
        this.dict_sort = dict_sort;
    }

    public char getDict_enable() {
        return dict_enable;
    }

    public void setDict_enable(char dict_enable) {
        this.dict_enable = dict_enable;
    }

    public String getDict_memo() {
        return dict_memo;
    }

    public void setDict_memo(String dict_memo) {
        this.dict_memo = dict_memo;
    }

}
