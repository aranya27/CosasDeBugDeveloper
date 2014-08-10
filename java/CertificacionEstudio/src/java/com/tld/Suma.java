package com.tld;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Suma extends SimpleTagSupport{
    private int operando1;
    private int operando2;
    
    @Override
    public void doTag() throws javax.servlet.jsp.JspException, java.io.IOException {
        JspWriter out = getJspContext().getOut();
        out.print(operando1+operando2);
    }
    
    public int getOperando1() {
        return operando1;
    }

    public void setOperando1(int operando1) {
        this.operando1 = operando1;
    }

    public int getOperando2() {
        return operando2;
    }

    public void setOperando2(int operando2) {
        this.operando2 = operando2;
    }
}



