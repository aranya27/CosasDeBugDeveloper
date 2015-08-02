from nodo import *

nodo1 = Node(1)
nodo2 = Node(2)
nodo3 = Node(3)
nodo4 = Node(2)
nodo5 = Node(3)
nodo6 = Node(9)

nodo1.next = nodo2
nodo2.next = nodo3
nodo3.next = nodo4
nodo4.next = nodo5
nodo5.next = nodo6
nodo1 = borrarRepetidos(nodo1)
imprimirTodo(nodo1)



