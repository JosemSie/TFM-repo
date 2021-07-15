/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Jose Manuel
 */
public abstract class Session {
    private String lastURI;
    private Object id;
    public Session(Object paramId){
        this.id = paramId;
    }
    public Session(Object paramId, String paramLastURI){
        this.id = paramId;
        this.lastURI = paramLastURI;
    }
    public String getLastURI(){
        return this.lastURI;
    }
    public void setLastURI(String newURI){
        this.lastURI = newURI;
    }
    public Object getId(){
        return this.id;
    }
    public abstract boolean isEqual(Session p1);
}
