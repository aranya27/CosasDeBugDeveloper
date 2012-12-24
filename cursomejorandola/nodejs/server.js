var express = require("express");
var app = express();
var mensajes = [],
	peticionesPendientes = []
	;


function holaMundo(req,res){
	res.send("hola mundo");
}
function mensaje(req,res){
	mensajes.push(req.params.mensaje);
	peticionesPendientes.forEach(function (res){
		res.send(mensajes+"<script>window.location.reload()</script>");

	});


	res.send("Gracias por tu mensaje: "+req.params.mensaje);
}
function listarMensajes(req,res){
	/*res.send(mensajes+"<script>setTimeout(function(){window.location.reload()},5000)</script>");*/
	peticionesPendientes.push(res);
}


//ME QUEDE EN EL MINUTO 30

app.get("/",holaMundo);
app.get("/mensajes/new/:mensaje", mensaje);
app.get("/mensajes/list",listarMensajes);



app.listen(3000);