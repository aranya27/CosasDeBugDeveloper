
def ponerCeros(matriz):
	totalLineas = len(matriz)
	totalColumnas = len(matriz[0])

	listaBorrar = []
	i = 0
	
	for row in matriz:
		j = 0
		for e in row:
			if e == 0:
				listaBorrar.append([i,j])
			j = j+1
		i = i+1
	
	for lb in listaBorrar:
		for i in range(totalLineas):
			matriz[i][lb[1]] = 0
		for i in range(totalColumnas):
			matriz[lb[0]][i] = 0





		
matriz = [
	[ 1 ,1 ,1 ,1 ,1],
	[ 1 ,0 ,1 ,1 ,1],
	[ 1 ,1 ,1 ,1 ,1],
	[ 1 ,1 ,1 ,0 ,1],
	[ 1 ,1 ,1 ,1 ,1]
]

ponerCeros(matriz)




for r in matriz:
	print r

