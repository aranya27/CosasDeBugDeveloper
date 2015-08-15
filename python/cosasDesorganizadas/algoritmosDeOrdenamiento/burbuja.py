#!/usr/bin/python


class Burbuja(object):

	def __init__(self, arreglo):
		self.arreglo = arreglo

	""" BURBUJA
	def ordena(self):
		iteracion = 1
		n = len(self.arreglo)
		
		for i in range(1,n):
			for j in range(0,n-1):
				if self.arreglo[j] > self.arreglo[j+1] :
					aux = self.arreglo[j]
					self.arreglo[j] = self.arreglo[j+1] 
					self.arreglo[j+1] = aux
				print "iteracion %s  arreglo = %s" %( iteracion,self.arreglo )
				iteracion = iteracion + 1
	"""
	
	"""BURBUJA CON MENOS CODIGO"""
	def ordena(self):
		iteracion = 1
		for passnum in range(len(self.arreglo)-1,0,-1):
			for i in range(passnum):
				if self.arreglo[i]>self.arreglo[i+1]:
					temp = self.arreglo[i]
					self.arreglo[i] = self.arreglo[i+1]
					self.arreglo[i+1] = temp
				print "iteracion %s  arreglo = %s" %( iteracion,self.arreglo )	
				iteracion = iteracion + 1

		
		

	"""BURBUJA MEJORADO 
	def ordena(self):
		iteracion = 1
		n = len(self.arreglo)
		i = 1
		ordenado = False

		while (i<n and ordenado == False):
			i = i + 1
			ordenado = True
			for j in range(0, n-1):
				if self.arreglo[j] > self.arreglo[j+1] :
					ordenado = False
					aux = self.arreglo[j]
					self.arreglo[j] = self.arreglo[j+1] 
					self.arreglo[j+1] = aux
				print "iteracion %s  arreglo = %s" %( iteracion,self.arreglo )
				iteracion = iteracion + 1
	"""
b = Burbuja([2,7,3,93,1,4,33,50,5])
b.ordena()
print "Final = %s" %b.arreglo
