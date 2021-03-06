class MegaAnfitrion
	attr_accessor :nombres
	
	def initialize(nombres="Anonimo")
		@nombres = nombres
	end

	def decir_hola
		if @nombres.nil?
			puts "…………..."
		elsif	@nombres.respond_to?("each")
			@nombres.each do |nombre|
				puts "Hola #{nombre}"
			end
		else
			puts "Hola #{@nombres}"
		end
	end


	def decir_adios
		if @nombres.nil?
			puts "………."
		elsif @nombres.respond_to?("join")
			puts "Adios #{nombres.join(", ")}. Vuelvan pronto"
		else
			puts "Adios #{nombres.to_s}. Vuelve pronto"
		end
	end
end



if __FILE__ == $0
	ma = MegaAnfitrion.new(["pedro","carlos"])
	ma.decir_hola
	puts "file = #{__FILE__}"

	ma2 = MegaAnfitrion.new
	ma2.decir_adios
end


		
	

		
		
