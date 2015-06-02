def fizzbuzz(n):
	resultado = ""
	resultado = "Fizz" if n%3==0 else ""
	resultado = resultado+"Buzz" if n%5==0 else resultado
	resultado = n if resultado=="" else resultado
	return resultado
	

for i in range(1,100):
	print "%s" % fizzbuzz(i)
	
