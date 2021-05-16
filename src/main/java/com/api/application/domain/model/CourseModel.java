package com.api.application.domain.model;

import com.api.application.domain.protocol.course.CourseInterface;

public class CourseModel implements CourseInterface {

    private String id;
    private String name;
    private String description;
    private TopicModel topic;

    public CourseModel() {
    }

    public CourseModel(String id, String name, String description, TopicModel topic) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public TopicModel getTopic() {
        return topic;
    }

    public void setTopic(TopicModel topic) {
        this.topic = topic;
    }
}
