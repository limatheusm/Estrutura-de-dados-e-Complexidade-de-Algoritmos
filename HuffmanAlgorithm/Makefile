SOURCE = $(wildcard src/*.java)
CLASS = $(subst src, build, $(SOURCE:.java=.class))
 
build: $(SOURCE) | file
	@javac $^ -d ./build
	@echo Construção concluída com êxito!

file:
	@mkdir -p build

run: $(CLASS)
	@java -cp build/ Main

clean:
	@rm -f -r build
	@rm -f -r logs
	@echo Logs e arquivos objeto apagados com êxito!