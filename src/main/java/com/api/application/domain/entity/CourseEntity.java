package com.api.application.domain.entity;

import com.api.application.domain.model.CourseModel;
import com.api.application.domain.model.TopicModel;
import com.api.application.domain.protocol.course.CourseInterface;
import com.api.application.domain.protocol.topic.TopicInterface;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = CourseEntity.TABLE_NAME)
public class CourseEntity implements CourseInterface {
    public static final String TABLE_NAME = "COURSES";

    public CourseEntity() {
    }

    public CourseEntity(CourseModel courseModel) {
        this.id = courseModel.getId();
        this.name = courseModel.getName();
        this.description = courseModel.getDescription();
        this.topic = new TopicEntity(courseModel.getTopic());
    }

    public CourseEntity(String id, String name, String description, TopicEntity topic) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic = topic;
    }

    @Id
    private String id;
    private String name;
    private String description;
    @ManyToOne
    private TopicEntity topic;

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

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }
}
