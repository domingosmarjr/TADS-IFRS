#!/bin/bash  

git pull
git add *
git commit -m 'atualizando'
git push

repositorio=${PWD##*/}
echo "# $repositorio <br>" > "README.md"
echo "# $repositorio <br>" > "./../"$repositorio".wiki/Home.md"

# listando tudo interno ao repositorio
for d in ./*; do

	if [[ "$d" != "./README.md" && "$d" != "./atualizar.sh" && "$d" != "./nova_aula.sh" ]]; then
		echo "## [$d](https://github.com/IgorAvilaPereira/"$repositorio"/tree/main/$d) <br>" >> "README.md"		
		# se n tiver wiki, favor comentar
		echo "## [$d](https://github.com/IgorAvilaPereira/"$repositorio"/tree/main/$d) <br>" >> "./../"$repositorio".wiki/Home.md"		
		cd $d							

		# documentos do subdiretorio de cada aula
		for d2 in *.*; do
		    if [[ "$d2" != "README.md" && "$d2" != "*.*" ]]; then		
	    		echo "[$d2](https://github.com/IgorAvilaPereira/"$repositorio"/blob/main/$d/$d2) <br>" >> "./../README.md"		
	    		# se n tiver wiki, favor comentar
    			echo "[$d2](https://github.com/IgorAvilaPereira/"$repositorio"/blob/main/$d/$d2) <br>" >> "./../../"$repositorio".wiki/Home.md"					
            else
                if [[ "$d2" != "*.*" ]]; then		
                    cat "README.md" >> "./../README.md"
                    cat "README.md" >> "./../../"$repositorio".wiki/Home.md"	
                fi 						
			fi
		done

		# subdiretorios internos de cada aula
		for i in */; do 			
			if [[ -d "$i" && ! -L "$i" ]]; then
				echo "[${i%%/}](https://github.com/IgorAvilaPereira/"$repositorio"/blob/main/$d/${i%%/}) <br>" >> "./../README.md"		
				# se n tiver wiki, favor comentar
				echo "[${i%%/}](https://github.com/IgorAvilaPereira/"$repositorio"/blob/main/$d/${i%%/}) <br>" >> "./../../"$repositorio".wiki/Home.md"	
			fi
		done
		cd ..
	fi
done

git add * && git commit -m 'atualizando' && git push
# se n tiver wiki, favor comentar
cd "./../"$repositorio".wiki/" && git add * && git commit -m 'atualizando' && git push
# exibindo o novo README criado. Este mesmo README aparecera no Home.md da wiki (caso exista)
echo "==================================="
cd "./../"$repositorio"/" && cat "README.md"	
echo "==================================="
