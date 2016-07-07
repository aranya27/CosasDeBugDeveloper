


def hanoi(numDiscos, origen, auxiliar, destino):

	if numDiscos == 1:
		print 'Mover disco de %s a %s' % (origen, destino)
	else:
		hanoi(numDiscos-1, origen, destino, auxiliar)
		print 'Mover disco de %s a %s' % (origen, destino)
		hanoi(numDiscos-1, auxiliar, origen, destino)


if __name__ == "__main__":

	hanoi(3,"Torre 1","Torre 2","Torre 3")
