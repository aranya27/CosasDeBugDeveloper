from nodo import *

nodo1 = Node(1)
nodo2 = Node(2)
nodo3 = Node(3)
nodo4 = Node(4)


nodo1.next = nodo2
nodo2.next = nodo3
nodo3.next = nodo4
imprimirTodoAlreves(nodo1)
nodo1 = borrarNodo(4,nodo1)
imprimirTodo(nodo1)



