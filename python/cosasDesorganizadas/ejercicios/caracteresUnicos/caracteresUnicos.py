
def tieneCaracteresUnicos(cadena):
	
	for c in cadena:
		contador = 0
		for i in range(1,len(cadena)):
			if c == cadena[i]:
				contador += 1
			if contador > 1:
				return False
	return True

cadena = "abcdecfg"
resultado = tieneCaracteresUnicos(cadena)
print "La cadena {} tiene todos sus caracteres unicos?  R={}".format(cadena,str(resultado))
