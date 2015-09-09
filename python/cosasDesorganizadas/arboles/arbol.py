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

def imprimirArbolOrdenado(arbol):
	if arbol.izq != None:
		imprimirArbolOrdenado(arbol.izq)
	print(arbol.valor)
	if arbol.der != None:
		imprimirArbolOrdenado(arbol.der)

	
# El arbol que se genera es el que aparece aqui https://es.wikipedia.org/wiki/%C3%81rbol_binario_de_b%C3%BAsqueda
def generarArbol():
	a = Arbol(8)
	a.addValor(3)
	a.addValor(1)
	a.addValor(6)
	a.addValor(4)
	a.addValor(7)
	a.addValor(10)
	a.addValor(14)
	a.addValor(13)
	return a



if __name__ == "__main__":
	a = generarArbol()
	imprimirArbolOrdenado(a)



	
