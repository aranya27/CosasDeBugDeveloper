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
			




















