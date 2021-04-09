package com.api.application.domain.entity;

import com.api.application.domain.protocol.TopicInterface;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = TopicEntity.TABLE_NAME)
public class TopicEntity implements TopicInterface{
    public static final String TABLE_NAME = "TOPICS";

    public TopicEntity() {
    }

    public TopicEntity(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Id
    private String id;
    private String name;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(this.id == null) this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
