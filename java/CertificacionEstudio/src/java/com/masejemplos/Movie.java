package com.masejemplos;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id @GeneratedValue
    private long id;
    private String title;
    private int year;
    
    public Movie() {}
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}








