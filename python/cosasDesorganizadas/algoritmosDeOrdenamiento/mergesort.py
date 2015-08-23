#!/usr/bin/python
import pdb

def mergeSort(lista):
	print("Splitting ",lista)
	if len(lista) > 1:
		mid = len(lista)//2
		left = lista[:mid]
		right = lista[mid:]

		mergeSort(left)
		mergeSort(right)

		i=0
		j=0
		k=0
		while i<len(left) and j<len(right):
			if left[i] < right[j]:
				lista[k] = left[i]
				i=i+1
			else:
				lista[k] = right[j]
				j=j+1
			k=k+1

		while i<len(left):
			lista[k] = left[i]
			i=i+1
			k=k+1

		while j<len(right):
			lista[k] = right[j]
			j=j+1
			k=k+1

	print("Merging ",lista)
	return lista


lista = mergeSort([2,7,50,3,93,1,4,33,5])
"""pdb.set_trace()"""
print "Final = %s" %lista