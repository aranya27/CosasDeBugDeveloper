import sys

def getMatriz():
	matriz = [
			[1,2,3,4],
			[2,9,5,2],
			[3,7,3,6],
			[1,2,1,2]
		  ]
	return matriz

def imprimirUpper(matriz,n):
	j = 0
	
	for i in reversed(range(0,n+1)):
		sys.stdout.write( str( matriz[j][i] )+' ' )
		j = j+1
	print

def imprimirLower(matriz,n):
	j = len(matriz)-1
	
	for i in range(n,len(matriz) ):
		
		sys.stdout.write( str( matriz[i][j] )+' ' )
		j = j-1
	print	

def imprimirDiagonal(matriz):
	for i in range(len(matriz)):
		imprimirUpper(matriz,i)

	for i in range(1,len(matriz)):
		imprimirLower(matriz,i)


if __name__ == "__main__":
	matriz = getMatriz()
	imprimirDiagonal(matriz)
