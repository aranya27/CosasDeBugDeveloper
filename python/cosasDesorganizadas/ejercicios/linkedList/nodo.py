class Node:
    def __init__(self, cargo=None, next=None):
        self.cargo = cargo
        self.next  = next

    def __str__(self):
        return str(self.cargo)



def imprimirTodo(nodo):
	while nodo:
		print(nodo)
		nodo = nodo.next


def imprimirTodoAlreves(nodo):
	if nodo.next:
		imprimirTodoAlreves(nodo.next)
	print(nodo)



def borrarNodo(cargo,nodo1):
	nodo = nodo1
	
	if nodo.cargo == cargo:
		return nodo.next

	while nodo.next:
		if nodo.next.cargo == cargo:
			nodo.next = nodo.next.next
			return nodo1
		nodo = nodo.next
	return nodo1
			



def borrarRepetidos(nodo):	
	nodoActual = nodo
	while nodoActual:
		nodoIter = nodoActual.next
		nodoPrev = nodoActual
		while nodoIter:
			if nodoActual.cargo == nodoIter.cargo:
				nodoPrev.next = nodoIter.next
			else:
				nodoPrev = nodoIter
			nodoIter = nodoIter.next
		nodoActual = nodoActual.next
	return nodo

"""
nodo1 = Node(1)
nodo2 = Node(2)
nodo3 = Node(3)
nodo4 = Node(4)
nodo5 = Node(3)
nodo6 = Node(3)
"""










