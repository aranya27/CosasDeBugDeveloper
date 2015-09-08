class Arbol(object):
	
	def __init__(self,valor):
		self.valor = valor;
		self.izq = None
		self.der = None

	def addValor(self,valor):
		if valor == self.valor:
			return
		
		if valor > self.valor:
			if self.der == None:
				self.der = Arbol(valor)
			else:
				self.der.addValor(valor)
		else:
			if self.izq == None:
				self.izq = Arbol(valor)
			else:
				self.izq.addValor(valor)


def imprimirProfundidad(arbol):
	if arbol.izq != None:
		imprimirProfundidad(arbol.izq)
	print(arbol.valor)
	if arbol.der != None:
		imprimirProfundidad(arbol.der)
	



if __name__ == "__main__":
	a = Arbol(2)
	a.addValor(3)
	a.addValor(1)
	a.addValor(9)
	a.addValor(10)
	a.addValor(.5)
	imprimirProfundidad(a)
	


	
