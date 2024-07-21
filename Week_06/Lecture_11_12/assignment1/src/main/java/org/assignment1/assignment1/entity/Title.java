package org.assignment1.assignment1.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "titles")
public class Title {

    @EmbeddedId
    private TitleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empNo")
    @JoinColumn(name = "emp_no")
    @JsonIgnore
    private Employee employees;

    @Column(name = "to_date", nullable = false)
    private Date toDate;

    public Integer getEmpNo() {
        return this.id != null ? this.id.getEmpNo() : null;
    }

    public void setEmpNo(Integer empNo) {
        if (this.id == null) {
            this.id = new TitleId();
        }
        this.id.setEmpNo(empNo);
    }

    public String getTitle() {
        return this.id != null ? this.id.getTitle() : null;
    }

    public void setTitle(String title) {
        if (this.id == null) {
            this.id = new TitleId();
        }
        this.id.setTitle(title);
    }

    public Date getFromDate() {
        return this.id != null ? this.id.getFromDate() : null;
    }

    public void setFromDate(Date fromDate) {
        if (this.id == null) {
            this.id = new TitleId();
        }
        this.id.setFromDate(fromDate);
    }

}
