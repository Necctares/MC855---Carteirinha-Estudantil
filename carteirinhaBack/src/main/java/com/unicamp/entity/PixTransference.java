package com.unicamp.entity;

import java.sql.Date;
import org.json.JSONObject;

@Entity
@Table(name="pix_transference")
public class PixTransference {

    @Id
    @Column(name="bb_id", nullable = false)
    String bb_id;
    @Column(name="ra", nullable = false)
    Integer ra;
    @Column(name="date", nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm")
    Date date;
    @Column(name="active", nullable = false)
    Boolean isActive;
    @Column(name="completed", nullable = false)
    Boolean isCompleted;
    @Column(name="expired", nullable = false)
    Boolean isExpired;
    @Column(name="value", nullable = false)
    Double value;

    public PixTransference(){}
    
    public String getId(){
        return this.bb_id;
    }
    
    public Date getDate(){
        return this.date;
    }

    public Double getValue(){
        return this.value;
    }
    
    @JsonProperty("active")
    public Boolean isActive(){
        return this.isActive;
    }
    
    @JsonProperty("completed")
    public Boolean isCompleted(){
        return this.isCompleted;
    }
    
    @JsonProperty("expired")
    public Boolean isExpired(){
        return this.isExpired;
    }

    @JsonProperty("active")
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("completed")
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @JsonProperty("expired")
    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }
    
}
