# Wooloo - Projeto de C206/C207

RPG desenvolvido como projeto final das disciplinas de Programação Orientada à Objetos e Banco de Dados.
### Grupo
1. Henrique Barcia Lang
2. Gabriel Claret de Rezende
3. Rubens Cividati

## Diretórios importantes

* Diretório do executável (.jar): `namelessgame\namelessgame\dist`
	
* Diretório da documentação: `namelessgame\namelessgame\dist\javadoc`
	
* Diretório do script em Python: `namelessGame\namelessGame`

* Diretório do arquivo de texto com itens: `namelessGame\data`
	
* Diretório do modelo do Banco de Dados e seu backup .sql: `namelessGame\namelessGame`
	
OBS: Tudo relacionado à conexão com o Banco de Dados foi escrito no pacote namelessGame.Database.
	
## Requerimentos para o projeto

* Instalar fonte Oscine (.zip disponível no diretório do projeto)

* Para executar o script em Python: `pip install mysql-connector-python`
  - Link com mais detalhes: https://pynative.com/install-mysql-connector-python/

No código da loja alguns itens já estão configurados. Para que apareçam, é necessário criá-los primeiramente usando o script em Python (ou inserí-los no Banco de Dados diretamente).

Pode-se criá-los manualmente seguindo as instruções, alterando os valores como bem entender, ou copiar e colar os dados disponíveis no arquivo de texto mencionado em `Diretórios importantes` no ambiente de interpretação que o script está sendo executado (terminal do Windows, do Linux, do VS Code, etc.).

Caso resolva alterar o nome dos itens, para que apareçam na loja você deve configurar adequadamente no método
	`void fillShop()`
de Game.java (pacote Gameplay), seguindo o que já foi feito como exemplo.
