def girarMatriz(matriz):
	girarMatrizRecursivo(matriz, 0, len(matriz)-1 )

def girarMatrizRecursivo(matriz, inicio, fin):
	print "iniciando con {} y {}".format(inicio, fin)
	
	cont = 0
	for i in range(inicio, fin ):
		print "i = {}".format(i)
		aux = matriz[inicio][i]   # 1, 2, 3, 4
		aux2 = matriz[fin-cont][inicio]  # 13, 9 ,5 ,1
		aux3 = matriz[fin][fin-cont]   # 16, 15, 14 ,13
		aux4 = matriz[inicio+cont][fin] # 4, 8, 12, 16
		
		print "aux = {}".format(aux)
		print "aux2 = {}".format(aux2)
		print "aux3 = {}".format(aux3)
		print "aux4 = {}".format(aux4)
		print ""
		print ""
		
		
		matriz[fin-cont][inicio] = aux
		matriz[fin][fin-cont] = aux2
		matriz[inicio+cont][fin] = aux3
		matriz[inicio][i] = aux4

		cont = cont + 1

	if (inicio+1) < (fin-1):
		girarMatrizRecursivo(matriz, inicio+1, fin-1)

		
matriz = [
	[ 1,  2,  3,  4, 5],
	[ 6,  7,  8,  9, 10],
	[11, 12, 13, 14, 15],
	[16, 17, 18, 19, 20],
	[21, 22, 23, 24, 25]
]

girarMatriz(matriz)




for r in matriz:
	print r


