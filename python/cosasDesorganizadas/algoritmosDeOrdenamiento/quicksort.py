#!/usr/bin/python


class Quicksort(object):

	def __init__(self,arreglo):
		self.arreglo = arreglo

	def ordena(self):
		last = (len(self.arreglo)-1) 
		self.quicksort(self.arreglo, 0, last )

	def quicksort(self,arreglo,first,last):
		if (first >= last): 
			return

		pivoteIndex = (first + last) / 2
		pivote = arreglo[pivoteIndex]
		print "first = %s   last = %s" %(first,last)
		print "pivoteIndex = %s  valor pivote = %s" %(pivoteIndex, pivote)
		print "arreglo inicio = %s" %(arreglo)
		i = first
		j = last

		while i<j :
			print "i = %i, j = %i" % (i,j)
			while arreglo[i] < pivote:
				i += 1

			while arreglo[j] > pivote:
				j -= 1

			if i < j :
				print "intercambiando %s con %s" % (i,j)
				x = arreglo[i]
				arreglo[i] = arreglo[j]
				arreglo[j] = x
			else :
				break
			print "arreglo        = %s" %(arreglo)

		print "arreglo fin    = %s" %(arreglo)
		self.quicksort(arreglo, first, j-1)
		self.quicksort(arreglo, i+1, last)



b = Quicksort([2,7,50,3,93,1,4,33,5])
b.ordena()
print "Final = %s" %b.arreglo