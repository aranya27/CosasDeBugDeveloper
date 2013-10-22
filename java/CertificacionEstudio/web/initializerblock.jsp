
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!  	static String series="";
        
  	public void init()
	{
		System.out.println("this is init()-Parth");
		series=series+"init";
	}


	{  
    		 System.out.println("Execute only once - Instance intializer block."); 
		series=series+"initializer"; 
	}  
   
	static 
	{  
    		 System.out.println("Execute only once - Static block");
		series=series+"static";  
	}  
   
 public void jspInit() 
{  
         System.out.println("Execute only once - jspInit()"); 
	 series=series+"jspInit"; 
 }  
 %>  
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <br>
        <br>
        <%= series %>
        <h1>Hello World!</h1>
    </body>
</html>
