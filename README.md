# Wooloo - Projeto de C206/C207

RPG desenvolvido como projeto final das disciplinas de Programação Orientada a Objetos e Banco de Dados.
### Grupo
* [Gabriel Claret](https://github.com/claret1)
* [Henrique Lang](https://github.com/henriqueblang)
* [Rubens Cividati](https://github.com/Cividati)

## Diretórios importantes

* Diretório da documentação: `namelessGame\namelessGame\javadoc`
* Diretório do script em Python: `namelessGame\namelessGame\database`
* Diretório do arquivo de texto com itens: `namelessGame\namelessGame\database\data`
* Diretório do arquivo de texto com dungeons: `namelessGame\namelessGame\database\data`
* Diretório do arquivo de texto com monstros: `namelessGame\namelessGame\database\data`
* Diretório do modelo do Banco de Dados e seu backup .sql: `namelessGame\namelessGame\database\schema`

OBS: Tudo relacionado à conexão com o Banco de Dados foi escrito no pacote namelessGame.Database.
	
## Requerimentos para o projeto

* Instalar fonte Oscine (.zip disponível no diretório do projeto)

* Para conseguir executar o script em Python: `pip install mysql-connector-python`
  - Link com mais detalhes: https://pynative.com/install-mysql-connector-python/

No código da loja alguns itens já estão configurados. Para que apareçam, é necessário criá-los primeiramente usando o script em Python (ou inseri-los no Banco de Dados diretamente).

Pode-se criá-los manualmente seguindo as instruções, alterando os valores como bem entender, ou copiar e colar os dados disponíveis no arquivo de texto mencionado em `Diretórios importantes` no ambiente de interpretação que o script está sendo executado (terminal do Windows, do Linux, do VS Code, etc.).

Para novas dungeons e monstros, o processo é semelhante. Caso não queira criá-los manualmente, pode-se encontrar dados prontos em seus respectivos arquivos de texto, cujo diretório foi especificado em `Diretórios importantes`. Basta apenas copiar **todo** o texto e colar no ambiente de interpretação.

#### Ordem para inserir:
1. Itens
2. Dungeons
3. Monstros

Caso resolva alterar o nome de itens presentes na loja, deve-se configurar de acordo no método `void fillShop()` de Game.java (namelessGame.Gameplay), seguindo o que já foi feito como exemplo.

Use `addItemToShop` para adicionar itens que possam ser **comprados** na loja e `addItemToSell` para itens que possam ser **vendidos** na loja.
