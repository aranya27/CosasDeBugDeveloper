def remueveCaracteresRepetidos(cadena):
	i = 0
	while i < len(cadena):
		c = cadena[i]
		j = i+1
		while j < len(cadena):
			if cadena[j] == c:
				cadena = cadena[:j] + cadena[j+1:]
			j = j+1
		i = i+1
					
	return cadena	


cadena = "guadalajara"

print remueveCaracteresRepetidos(cadena)




