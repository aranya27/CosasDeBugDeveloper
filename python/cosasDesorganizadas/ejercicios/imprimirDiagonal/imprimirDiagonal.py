
def diagonal(matriz):
	upperDiagonal(matriz)
	lowerDiagonal(matriz)


def upperDiagonal(matriz):
	length = len(matriz) 
	for pos in range(length):
		i = 0
		j = pos
		while(j>=0):
			print(matriz[i][j], end=" ")
			i=i+1
			j=j-1
		print()


def lowerDiagonal(matriz):
	length = len(matriz) 

	for pos in range(1,length):
		i = pos
		j = length-1
		while(i<length):
			print(matriz[i][j], end=" ")
			i = i+1
			j = j-1
		print()




if __name__=="__main__":
	"""
	matriz = [
		[1,2,3],
		[4,5,6],
		[7,8,9]
	]
	"""
	matriz = [
		[9, 3, 2, 4],
		[8, 6, 1, 2],
		[5, 5, 6, 7],
		[1, 2, 8, 4]
	]

	diagonal(matriz)


