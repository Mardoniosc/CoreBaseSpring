#!/bin/sh
#
# Script MicroService Core Base v1.0
#
# chkconfig: 2345 80 20
# VARIAVEIS DO SCRIPT
PATH_MS=/opt/microservice
PATH_FONTE=/opt/core-base-api-rest
PATH_FILE_LOG=/var/log/core-base-api-rest.log

#ACESSANDO O PATH AONDE ENCONTRA-SE OS ARQUIVOS FONTES
cd $PATH_FONTE
#ATUALIZANDO OS ARQUIVOS 

#RETURN_GIT=`git pull | grep 'Already up-to-date'`

#echo "retorno: $RETURN_GIT"

if ! git pull | grep 'Already up-to-date'
then
    #AGUARDANDO 5 SEGUNDOS
    sleep 5
    #EXECUTANDO O MAVEN PARA GERACAO DO PACOTE
    mvn clean install
    #AGUARDANDO 5 SEGUNDOS
    sleep 5
    #BUSCANDO O ULTIMO ARQUIVO .JAR GERADO NA PASTA TARGET
    #CASO A GERACAO DO PACOTE FALHE, ELE PEGARA A ULTIMCA VERSAO GERADA.
    PATH_FILE=`ls $PATH_FONTE/target/*.jar -t | head -1`
    #COPIANDO O ARQUIVO .JAR GERADO PARA O LOCAL PADRAO
    cp -f $PATH_FILE $PATH_MS/core-base-api-rest
    #COPIANDO O ARQUIVO .CONF PARA O LOCAL PADRAO 
    #cp -f $PATH_FONTE/institutodepilar.conf $PATH_MS/institutodepilar.conf
    #PARANDO O SERVICO JA MAPEADO
    
    #Esse arquivo é uma copia do jar que foi gerado anteriormente.
    systemctl stop core-base-api-rest
    #AGUARDANDO 5 SEGUNDOS
    sleep 5
    #INICIANDO O SERVICO NOVAMENTE
    systemctl start core-base-api-rest
    #MOSTRANDO O LOG DA EXECUCAO
    tail -f $PATH_FILE_LOG
else
   echo "Servidor Backend ja atualizado."
fi
######################################
