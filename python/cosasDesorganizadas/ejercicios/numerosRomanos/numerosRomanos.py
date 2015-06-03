valoresDict = [
	['V','I'],
	['L','X'],
	['D','C'],
	['M','M']
]


def getNumeroRomano(index, n):
	resultado = ""
	if n<=3 :
		for i in range(0,n):
			resultado += valoresDict[index][1]
	elif n == 4 :
		resultado = valoresDict[index][1] + valoresDict[index][0]

	elif n == 5 :
		resultado = valoresDict[index][0]

	elif n > 5 and n < 9:
		resultado = resultado = valoresDict[index][0]
		for i in range(5,n):
			resultado += valoresDict[index][1]	
	elif n == 9 :
		resultado = valoresDict[index][1] + valoresDict[index+1][1]		

	return resultado


def obtenerNumeroRomano(n):
	numString = str(n)
	resultado = ""
	
	for index,s in enumerate(reversed(numString)):
		resultado = str(getNumeroRomano(index,int(s))) + resultado

	return resultado


resultado = obtenerNumeroRomano(input("Enter a number: "))
print "resultado = {}".format(resultado)




