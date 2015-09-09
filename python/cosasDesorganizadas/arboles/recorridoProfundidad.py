import arbol

def preorden(a):
	print(a.valor)
	if a.izq != None:
		preorden(a.izq)
	if a.der != None:
		preorden(a.der)

def inorden(a):
	if a.izq != None:
		inorden(a.izq)
	print(a.valor)
	if a.der != None:
		inorden(a.der)

def posorden(a):
	if a.izq != None:
		posorden(a.izq)
	if a.der != None:
		posorden(a.der)
	print(a.valor)


if __name__ == "__main__":
	a = arbol.generarArbol()
	#preorden(a)
	#inorden(a)
	posorden(a)
