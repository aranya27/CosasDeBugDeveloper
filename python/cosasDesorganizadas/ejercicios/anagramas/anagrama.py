

def checkAnagram(w1, w2):
	if len(w1) != len(w2):
		return False

	isAnagram = True

	for l in w1:
		if l not in w2:
			return False
	
	return True




if __name__ == "__main__":
	palabras = ("ROMA", "OMAR", "XDF", "PEPITO", "LOCO", "COLO")
	resultadoFinal = []

	

	for palabra in palabras:
		isAnagram = False
		for listaPalabras in resultadoFinal:
			if checkAnagram(palabra, listaPalabras[0]):
				listaPalabras.append(palabra)
				isAnagram = True
				break

		if not isAnagram:
			resultadoFinal.append([palabra])


	print resultadoFinal






