package com.main.java.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private String pic;
    private String remark;
    private String xremark;
    private Timestamp date;
    private Byte commend;
    private Byte open;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< Updated upstream
=======
    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid")
    public Category getCategory(){
        return this.category;
    }


>>>>>>> Stashed changes
    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "pic", nullable = true, length = 300)
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = -1)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "xremark", nullable = true, length = -1)
    public String getXremark() {
        return xremark;
    }

    public void setXremark(String xremark) {
        this.xremark = xremark;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "commend", nullable = true)
    public Byte getCommend() {
        return commend;
    }

    public void setCommend(Byte commend) {
        this.commend = commend;
    }

    @Basic
    @Column(name = "open", nullable = true)
    public Byte getOpen() {
        return open;
    }

    public void setOpen(Byte open) {
        this.open = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(pic, product.pic) &&
                Objects.equals(remark, product.remark) &&
                Objects.equals(xremark, product.xremark) &&
                Objects.equals(date, product.date) &&
                Objects.equals(commend, product.commend) &&
                Objects.equals(open, product.open);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, pic, remark, xremark, date, commend, open);
    }
}
