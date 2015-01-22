angular.module('aerolinea', ['ngRoute'])
	.config(rutaAerolinea);

	function rutaAerolinea($routeProvider){
		$routeProvider
			.when('/',{templateUrl: 'parciales/destinos.html',
				controller: 'destinosControl'})
			.when('/Vuelos',{template: '<h3>Vuelos</h3>',
				controller: 'vuelosControl'})
			.when('/Reservaciones',{template: '<h3>Reservaciones</h3>',
				controller: 'reservacionesControl'});
	}