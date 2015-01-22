function AppControl($scope){
	$scope.setActive = function(type){
		$scope.destinosActive = '';
		$scope.vuelosActive = '';
		$scope.reservacionesActive = '';

		$scope[type +"Active"] = 'active';
	}

	$scope.aeropuertos = {
		"SCI":{
			"codigo":"SCI",
			"nombre":"Aeropuerto de Paramillo",
			"ciudad":"San cristobal",
			"Pais":"Venezuela",
			"destinos":["LAX","SFO"]
		},
		"PBL":{
			"codigo":"PBL",
			"nombre":"Aeropuerto de PBL",
			"ciudad":"San X",
			"Pais":"Venezuela",
			"destinos":["LAX","MKE"]
		},
		"PBLX":{
			"codigo":"PBLX",
			"nombre":"AeropuertÓ de PBLX",
			"ciudad":"San X",
			"Pais":"Venezuela",
			"destinos":["LAX","MKE"]
		}
	};

	$scope.formularioURL='parciales/formulario.html';
	$scope.sidebarURL='parciales/airport.html'
	$scope.aeropuertoActual = null;
	$scope.editar = null;
	$scope.setAeropuerto=function(codigo){
		$scope.aeropuertoActual=$scope.aeropuertos[codigo];
	}
	$scope.editarAeropuerto=function(codigo){
		$scope.editar=$scope.aeropuertos[codigo];
	}
}