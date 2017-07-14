#!/bin/sh

#O path ou IP dado como argumento estará em $1 e o user, caso haja, estará em $2

SCRIPT=$(readlink -f "$0")
CURPATH=$(dirname "$SCRIPT") #Caminho do script sendo executado, onde será criado o novo diretório

mkdir -p "${CURPATH}/directory" #Cria o diretório

i=1
while [ $i -le  100 ];
do
	echo "$i" > "${CURPATH}/directory/${i}.txt" #Cria arquivos n.txt com n de 1 a 100 e com conteúdo de texto apenas "n"
	i=$((i+1))
done

cd ${CURPATH}/directory #Muda o working directory para o diretório criado
zip -r "${CURPATH}/compact.zip" ./* #Zipa todos os arquivos do diretório criado para o caminhos do script com nome compact.zip

if [ -d "$1" ] #Checa se o endereço passado é um diretório local (se não for, assume que é um IP)
then
	cp "${CURPATH}/compact.zip" "${1}/compact.zip" #Copia compact.zip para o path dado como argumento do script
	echo "File copied to given directory"
else 
	scp "${CURPATH}/compact.zip" "${2}@${1}:Desktop" #Envia para o desktop de user@IP (usando desktop, mas poderia ser um path dado)
	echo "File sent"
fi
