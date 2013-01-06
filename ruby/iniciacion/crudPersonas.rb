# encoding: utf-8

SALIDA = 4
PERSONASAGREGADAS = []

class Persona

	attr_accessor :nombre,:edad,:fechaNac

	def initialize(nombre,edad,fechaNac)
		@nombre = nombre
		@edad = edad
		@fechaNac = fechaNac
	end

	def initialize
	end
end

class Musico < Persona
	attr_accessor :instrumentos

	def initialize(nombre,edad,fechaNac,instrumentos)
		@nombre = nombre
		@edad = edad
		@fechaNac = fechaNac
		@instrumentos = instrumentos
	end

	def initialize
	end
end

class Programador < Persona
	attr_accessor :lenguajes

	def initialize(nombre,edad,fechaNac,lenguajes)
		@nombre = nombre
		@edad = edad
		@fechaNac = fechaNac
		@lenguajes = lenguajes
	end

	def initialize
	end
end




def printMenuPrincipal
	puts "Seleccione una opción"

	puts "1. Agregar persona"
	puts "2. Consultar persona"
	puts "3. Ver listado completo de personas"
	puts "4. Salir"
	puts "Opción: "
end

def menuPrincipal
	menuYaMostrado = false
	opc = 0

	begin
		if menuYaMostrado == true
			puts "La opción #{opc} no es reconocida, vuelva a intentarlo"
			puts
		end
		menuYaMostrado = true

		printMenuPrincipal
		opc = gets.chomp.to_i

	end while  not((1..4) === opc)
	return opc
end

def capturarNuevaPersona
	p = nil
	menuYaMostrado = false
	opc = 0

	until (1..2) === opc do
		if menuYaMostrado == true
			puts "La opción #{opc} no es reconocida, vuelva a intentarlo"
			puts
		end
		menuYaMostrado = true

		puts "¿Que desea capturar?"
		puts "1. Un músico"
		puts "2. Un programador"

		opc = gets.chomp.to_i
	end

	if opc == 1
		capturarNuevoMusico
	else
		capturarNuevoProgramador
	end
end



def capturarNuevoMusico
	p = Musico.new
	puts "Dame el nombre:"
	p.nombre = gets.chomp
	puts "Dame la edad en años:"
	p.edad = gets.chomp
	puts "Dame la fecha de nacimiento:"
	p.fechaNac = gets.chomp
	puts "Dame los instrumentos que toca:"
	p.instrumentos = gets.chomp

	PERSONASAGREGADAS[PERSONASAGREGADAS.length] = p
end

def capturarNuevoProgramador
	p = Programador.new
	puts "Dame el nombre:"
	p.nombre = gets.chomp
	puts "Dame la edad en años:"
	p.edad = gets.chomp
	puts "Dame la fecha de nacimiento:"
	p.fechaNac = gets.chomp
	puts "Dame los lenguajes de programacion que conoce:"
	p.lenguajes = gets.chomp

	PERSONASAGREGADAS[PERSONASAGREGADAS.length] = p
end

def imprimirListadoPersonas
	i = 1
	for p in PERSONASAGREGADAS
		if p.instance_of? Programador
			puts "#{i} nombre=#{p.nombre}. edad=#{p.edad}. Fecha de nacimiento=#{p.fechaNac}. Lenguajes que sabe=#{p.lenguajes}"
		elsif p.instance_of? Musico
			puts "#{i} nombre=#{p.nombre}. edad=#{p.edad}. Fecha de nacimiento=#{p.fechaNac}. Instrumentos que toca=#{p.instrumentos}"
		end
		
	end
end

if __FILE__ == $0
	# Creamos un array con los instrumentos musicales disponibles
	instrumentos = ['Violin','Trompeta','Vihuela','Guitarrón','Guitarra','Trompeta Pocket']
	instrumentos = instrumentos.sort # ordenamos el array

	# Creamos un array de los lenguajes de programacion disponibles
	lenguajes = ['Java','Ruby','Python','C','C++','Objective-C','PHP','Cobol','Javascript']
	lenguajes = lenguajes.sort # ordenamos el array
	
	print "Empezando"
	STDOUT.sync = true
	60.times {
	  print "."
	  sleep 0.03
	}
	puts

	puts "======Programa crud de personas hecho para aprender a codear en Ruby======"
	salir = false

	while salir == false
		
		opc = menuPrincipal

		case opc
			when 1
				capturarNuevaPersona
			when 3
				imprimirListadoPersonas
			when 4
				salir = true
			else
				salir = true 
		end

	end


end
# http://www.tutorialspoint.com/ruby/ruby_loops.htm

















