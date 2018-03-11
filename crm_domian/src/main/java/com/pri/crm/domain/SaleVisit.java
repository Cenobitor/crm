package com.pri.crm.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 8:20 PM 25/02/2018
 * @Modified By:
 */
@Entity
@Table(name = "sale_visit")
public class SaleVisit {
    @Id
    @Column
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String visit_id;
    @Column
    private Date visit_time;
    @Column
    private Date visit_nexttime;
    @Column
    private String visit_addr;
    @Column
    private String visit_detail;

    @ManyToOne
    @JoinColumn(name = "visit_user_id")
    private User visit_user;
    @ManyToOne
    @JoinColumn(name = "visit_cust_id")
    private Customer visit_cust;
    @ManyToOne
    @JoinColumn(name = "visit_lkm_id")
    private LinkMan visit_lkm;

    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public Date getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(Date visit_time) {
        this.visit_time = visit_time;
    }

    public Date getVisit_nexttime() {
        return visit_nexttime;
    }

    public void setVisit_nexttime(Date visit_nexttime) {
        this.visit_nexttime = visit_nexttime;
    }

    public String getVisit_addr() {
        return visit_addr;
    }

    public void setVisit_addr(String visit_addr) {
        this.visit_addr = visit_addr;
    }

    public String getVisit_detail() {
        return visit_detail;
    }

    public void setVisit_detail(String visit_detail) {
        this.visit_detail = visit_detail;
    }

    public User getVisit_user() {
        return visit_user;
    }

    public void setVisit_user(User visit_user) {
        this.visit_user = visit_user;
    }

    public Customer getVisit_cust() {
        return visit_cust;
    }

    public void setVisit_cust(Customer visit_cust) {
        this.visit_cust = visit_cust;
    }

    public LinkMan getVisit_lkm() {
        return visit_lkm;
    }

    public void setVisit_lkm(LinkMan visit_lkm) {
        this.visit_lkm = visit_lkm;
    }
}
