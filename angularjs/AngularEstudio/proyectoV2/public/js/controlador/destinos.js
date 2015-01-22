function destinosControl($scope){
	$scope.setActive('destinos');
	$scope.sidebarURL='parciales/airport.html'
	$scope.aeropuertoActual = null;
	$scope.setAeropuerto=function(codigo){
		$scope.aeropuertoActual=$scope.aeropuertos[codigo];
	}
}