""" Usando una busqueda binaria, en estos casos se supone que el array en el cual buscar ya esta ordenado """


def chopIni(n, array):
	return chop(n, array,0, len(array) )
	
def chop(n, array, i, j):
	middle = (i+j)//2
		
	"""
	print("array[middle] = ",array[middle])	
	print("middle = ",middle)	
	print("i = ",i)	
	print("j = ",j)	
	print()
	"""	

	if middle > 0:
		
		if n > array[middle]:
			result = chop(n, array,(middle+1),j)
		elif array[middle] == n:
			result = middle
		else:
			result = chop(n, array, i, middle)
		
	elif array[middle] == n:
		result = 0
	else:
		result = -1
	
	return result



if __name__ == "__main__":
	print("result = ",chopIni(1, [1,2,3,4,5,6]) )
	print("result = ",chopIni(2, [1,2,3,4,5,6]) )
	print("result = ",chopIni(3, [1,2,3,4,5,6]) )
	print("result = ",chopIni(4, [1,2,3,4,5,6]) )
	print("result = ",chopIni(5, [1,2,3,4,5,6]) )
	print("result = ",chopIni(6, [1,2,3,4,5,6]) )
	print("result = ",chopIni(2, [1,2]) )
	
