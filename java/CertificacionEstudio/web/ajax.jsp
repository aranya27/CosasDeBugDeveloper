    <!DOCTYPE html>
    <html>
        <head>
            <script>
            function loadXMLDoc(){
                var xmlhttp;
                if (window.XMLHttpRequest){// C�digo para IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp=new XMLHttpRequest();
                }else{// C�digo para IE6, IE5
                    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange=function(){
                    if (xmlhttp.readyState==4 && xmlhttp.status==200){
                        document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET","/rutaAMiSitioWeb",true);
                xmlhttp.send();
            }
            </script>
        </head>
        <body>
            <div id="myDiv"><h2>Presiona el bot�n para cambiar el contenido de este div</h2></div>
            <button type="button" onclick="loadXMLDoc()">Cambiar contenido</button>
        </body>
    </html>

