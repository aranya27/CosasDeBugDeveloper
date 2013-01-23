# encoding: utf-8


def getMatriz
	matriz = [
			[1,2,3],
			[4,5,6],
			[7,8,9]]

	return matriz
end

def imprimirDiagonal(matriz)
	matriz.length.times{ |i| imprimirUpper(matriz,i)}

end

if __FILE__ == $0
	matriz = getMatriz

	imprimirDiagonal(matriz)
end