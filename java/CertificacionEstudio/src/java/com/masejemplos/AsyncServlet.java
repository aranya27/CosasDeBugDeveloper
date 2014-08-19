
package com.masejemplos;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns={"/asyncservlet"}, asyncSupported=true)
public class AsyncServlet extends HttpServlet {
    private MyRemoteResource resource;

    @Override
    public void init(javax.servlet.ServletConfig config) {
        resource = MyRemoteResource.create("config1=x,config2=y");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        final javax.servlet.AsyncContext acontext = request.startAsync();
        acontext.start(new Runnable() {
            public void run() {
                String param = acontext.getRequest().getParameter("param");
                String result = resource.process(param);
                HttpServletResponse response = (HttpServletResponse)acontext.getResponse();
                try {
                    /* ... Aqui imprimimos la respuesta ... */
                    java.io.PrintWriter out = response.getWriter();
                    out.println(result);
                    out.close();
                } catch (java.io.IOException ex) {
                    ex.printStackTrace();
                }
                acontext.complete();
            }
        });
    }
}



