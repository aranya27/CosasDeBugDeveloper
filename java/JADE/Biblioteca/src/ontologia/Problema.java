/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologia;

import jade.content.Concept;

/**
 *
 * @author armando
 */
public class Problema implements Concept {
    private int num;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    
    
}
