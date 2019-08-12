package com.alevel.project.coffee.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.text.SimpleDateFormat;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

    @CreatedDate
    @Column(name = "created")
    private Date creationDate;

    @LastModifiedDate
    @Column(name = "updated")
    private  Date lastModifiedDate;

    public String getCreationDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.y");
        return dateFormat.format(creationDate);
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate =  creationDate;
    }

    public String getLastModifiedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.y");
        return dateFormat.format(lastModifiedDate);
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
