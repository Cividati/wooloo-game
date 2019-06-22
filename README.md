# Wooloo - Projeto de C206/C207

RPG desenvolvido como projeto final das disciplinas de Programação Orientada a Objetos e Banco de Dados.
### Grupo
1. Henrique Barcia Lang
2. Gabriel Claret de Rezende
3. Rubens Cividati

## Diretórios importantes

* Diretório do executável (.jar): `namelessgame\namelessgame\dist`
	
* Diretório da documentação: `namelessgame\namelessgame\dist\javadoc`
	
* Diretório do script em Python: `namelessGame\namelessGame`

* Diretório do arquivo de texto com itens: `namelessGame\namelessGame\insertItems.txt`

* Diretório do arquivo de texto com dungeons: `namelessGame\namelessGame\insertDungeons.txt`

* Diretório do arquivo de texto com monstros: `namelessGame\namelessGame\insertMonsters.txt`
	
* Diretório do modelo do Banco de Dados e seu backup .sql: `namelessGame\namelessGame`
	
OBS: Tudo relacionado à conexão com o Banco de Dados foi escrito no pacote namelessGame.Database.
	
## Requerimentos para o projeto

* Instalar fonte Oscine (.zip disponível no diretório do projeto)

* Para conseguir executar o script em Python: `pip install mysql-connector-python`
  - Link com mais detalhes: https://pynative.com/install-mysql-connector-python/

No código da loja alguns itens já estão configurados. Para que apareçam, é necessário criá-los primeiramente usando o script em Python (ou inseri-los no Banco de Dados diretamente).

Pode-se criá-los manualmente seguindo as instruções, alterando os valores como bem entender, ou copiar e colar os dados disponíveis no arquivo de texto mencionado em `Diretórios importantes` no ambiente de interpretação que o script está sendo executado (terminal do Windows, do Linux, do VS Code, etc.).

Para as dungeons e monstros, é necessário inseri-los, assim como os itens, pelo script feito em Python (insert_values.py, mencionado em `Diretórios importantes`). Para fazer isso, basta copiar o texto do bloco de notas e colar no no ambiente de interpretação.

#### Ordem para inserir:
1. Itens
2. Dungeons
3. Monstros

Caso resolva alterar o nome dos itens, deve-se configurar de acordo no método `void fillShop()` de Game.java (namelessGame.Gameplay), seguindo o que já foi feito como exemplo.

Use `addItemToShop` para adicionar itens que possam ser **comprados** na loja e `addItemToBuy` para itens que possam ser **vendidos** na loja.

## Bugs conhecidos

 * Ao explorar uma dungeon após a mesma ter sido finalizada, o monstro pode ter sua HP disposta com um valor negativo. 
 **Solução:** reinicie o jogo.
 


