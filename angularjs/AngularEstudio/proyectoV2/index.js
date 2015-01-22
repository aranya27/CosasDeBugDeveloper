var http = require('http');
var port = process.env.port || 3000;
var express = require('express');
var handlebars = require('express3-handlebars');
var path = require('path');

var application = express();

//application.use(express.static(path.join(__dirname, 'public')));
application.use(express.static(__dirname + '/public'));

application.engine('handlebars', handlebars({ defaultLayout: 'main' }));

application.get('/', function(req, res){
    res.render('index.handlebars', { someProp: 3 });
});
console.log("puerto: "+port);
application.listen(port);