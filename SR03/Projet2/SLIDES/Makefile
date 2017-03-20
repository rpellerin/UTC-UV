all: slides.html

slides.html: slides.md ./style.html
	pandoc -t dzslides \
		--include-in-header=./style.html \
		-s slides.md \
		-o slides.html

slides.pdf: slides.tex
	pdflatex slides.tex

slides.tex: slides.md
	pandoc -t beamer \
		-s slides.md \
		-o slides.tex
