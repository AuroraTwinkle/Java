package com.main.java.model;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import javax.persistence.criteria.Join;
import java.util.Objects;

@Entity
public class Category {
    private int id;
    private String type;
    private Byte hot;
    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aid")
    public Account getAccount() {
        return this.account;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "hot", nullable = true)
    public Byte getHot() {
        return hot;
    }

    public void setHot(Byte hot) {
        this.hot = hot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(type, category.type) &&
                Objects.equals(hot, category.hot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, hot);
    }
}
