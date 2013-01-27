# encoding: utf-8


def getMatriz
	matriz = [
			[1,2,3],
			[4,5,6],
			[7,8,9]]

	return matriz
end

def imprimirUpper(matriz,j)
	
	for i in 0..j do
		print matriz[i][j].to_s+' '
		j = j-1
	end
	p ''
end

def imprimirLower(matriz,j)
	i = matriz.length-1
	for j in j..matriz.length-1 do
		print matriz[j][i].to_s+' '
		i = i-1
	end
	p ''
end

def imprimirDiagonal(matriz)
	matriz.length.times{ |i| imprimirUpper(matriz,i)}
	
	for i in 1..matriz.length do
		imprimirLower(matriz,i)
	end

	
end

if __FILE__ == $0
	matriz = getMatriz

	imprimirDiagonal(matriz)
end