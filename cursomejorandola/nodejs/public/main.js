var main = function (argument) {
	console.log('main');

	var grid = new Grid();
	grid.render( $('#grid') );

	window.client = io.connect(window.location.href);

	client.on('pintar', function (data) {
		grid.pintar(data.x, data.y, data.color);
	});



	client.on('modificaSpan', function (data) {
		document.getElementById("miSpan").innerHTML=data;
	});	
}

$(document).on('ready', main)

function a(){
	window.client.emit('modificaSpan',$('#miBoton').val());
	//document.getElementById("miSpan").innerHTML=$('#miBoton').val();
}
