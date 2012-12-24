//navigator 	Geolocalizacion aceleometros navegador useragent
//window 		Tab resolucion dimension posicion
//document		<html> DOM


//$(document).ready(alert("sss"));
$(document).on("ready",inicio);
function inicio()
{
	$("#perzonalizar").on("click", transicion);
}

function transicion()
{
	var cambiosCSS = 
	{
		margin: 0,
		overflow: "hidden",
		padding: 0,
		width: 0
	};
	
	var cambiosPerson = 
	{
		height: "auto",
		opacity: 1,
		width: "40%"
	};
	$("#historia").css(cambiosCSS);
	$("#personalizacion").css(cambiosPerson);
	$("#color div").on("click",cambiarColor);
}


function cambiarColor(datos)
{
	//console.log(datos);
	var col = datos.currentTarget.id;
	$("#cochecito img").attr("src","c"+col+".jpg");
}