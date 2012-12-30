/*
Correr en consola lo siguiente:
npm install
Es es para instalar todo lo que hay en el archivo package.json
*/

var express = require("express");
var app 	= express();
var server 	= require('http').createServer(app),
	io 		= require('socket.io').listen(server),
	cons 	= require('consolidate')
	;


server.listen(3000);

app.engine('.html',cons.jade);
app.set('view engine','html');

app.use(express.static('./public'));

app.get('/', function(req,res){
	res.render('index',{
		titulo : "hola"
	});
});

var i=1;
io.sockets.on('connection', function (socket){

	socket.on('pintar', function(data){
		socket.broadcast.emit('pintar',data);
	});

	socket.on('modificaSpan', function(data){
		//emitimos a todos los sockets, incluso a si mismo
		io.sockets.emit('modificaSpan',data+"_"+i);
		i++;
	});

});



