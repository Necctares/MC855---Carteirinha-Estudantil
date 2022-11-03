package com.unicamp.entity;

import java.sql.Date;

import org.json.JSONObject;

public class PixTransference {
    Student student;
    JSONObject bb_data;
    String id;
    String cpf;
    Date date;
    Boolean active;
    Boolean completed;
    Boolean expired;
    Boolean student_credited;

    public PixTransference(Student student, String bb_data_string){
        this.student = student;
        this.bb_data = new JSONObject(bb_data_string);
        this.id = this.bb_data.getString("txid");
        this.cpf = this.bb_data.getString("cpf");
        this.date = Date.valueOf(this.bb_data.getString("data"));
        this.student_credited = false;
        if (this.bb_data.getString("status") == "ATIVA"){
            this.active = true;
            this.completed = false;
            this.expired = false;
        } else if (this.bb_data.getString("status") == "CONCLUIDA"){
            this.active = false;
            this.completed = true;
            this.expired = false;
        } else {
            this.active = false;
            this.completed = false;
            this.expired = true;
        }
    }
    
    public Student getStudent(){
        return this.student;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getCpf(){
        return this.cpf;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public Boolean isActive(){
        return this.active;
    }
    
    public Boolean isCompleted(){
        return this.completed;
    }
    
    public Boolean isExpired(){
        return this.expired;
    }

    public JSONObject getBBData(){
        return this.bb_data;
    }

    public Boolean getStudentCredited(){
        return this.student_credited;
    }

    public void setStudentCredited(){
        this.student_credited = true;
    }

    public void setBBData(String bb_data_string){
        this.bb_data = new JSONObject(bb_data_string);
        if (this.bb_data.getString("status") == "ATIVA"){
            this.active = true;
            this.completed = false;
            this.expired = false;
        } else if (this.bb_data.getString("status") == "CONCLUIDA"){
            this.active = false;
            this.completed = true;
            this.expired = false;
        } else {
            this.active = false;
            this.completed = false;
            this.expired = true;
        }
    }
    
}
