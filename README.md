# Wooloo - Projeto de C206/C207

RPG desenvolvido como projeto final das disciplinas de Programação Orientada à Objetos e Banco de Dados.

## Diretórios importantes

* Diretório do executável (.jar): `namelessgame\namelessgame\dist`
	
* Diretório da documentação: `namelessgame\namelessgame\dist\javadoc`
	
* Diretório do script em Python: `namelessGame\namelessGame`
	
* Diretório do modelo do Banco de Dados e seu backup .sql: `namelessGame\namelessGame`
	
OBS: Tudo relacionado à conexão com o Banco de Dados foi escrito no pacote namelessGame.Database.
	
## Requerimentos para o projeto

* Instalar fonte Oscine (.zip disponível no diretório do projeto)

* Para executar o script em Python: `pip install mysql-connector-python`
  - Link com mais detalhes: https://pynative.com/install-mysql-connector-python/

No código da loja alguns itens já estão configurados. Para que apareçam, é necessário criá-los primeiramente usando o script em Python (ou inserindo no Banco de Dados diretamente).
Pode-se criá-los manualmente seguindo as instruções, alterando os valores como bem entender, ou copiar e colar os seguintes dados no ambiente de interpretação no qual o script está sendo executado (terminal do Windows, do Linux, do VS Code, etc.).
Caso resolva alterar o nome dos itens, para que apareçam na loja você deve configurar adequadamente no método
	`void fillShop()`
de Game.Java (pacote Gameplay), seguindo o que já foi feito como exemplo.

<details><summary>Itens</summary>
//inicio
1
Heavy Armor
heavyarmor1
0
10
0
15
0
2
1
1

1
Light Armor
lightarmor
0
0
15
10
0
2
1
1

1
Medium Armor
mediumarmor
0
5
5
15
0
2
1
1

1
Helmet
head
0
5
5
5
0
1
1
1

1
Shield+++
shield3
0
15
0
25
0
4
15
1

1
Shield
shield
0
5
0
10
0
4
10
1

1
Shield+
shield1
0
10
0
15
0
4
10
1

1
Shield++
shield2
0
15
0
20
0
4
10
1

1
Spear
spear
0
15
5
0
0
3
1
1

1
Sword
sword2
0
20
0
0
0
3
1
1

1
Crossbow
crossbow
0
10
10
0
0
3
1
1

1
Bow
bow
0
5
15
0
0
3
1
1

1
Dagger
dagger
0
10
10
0
0
3
1
1

1
Dagger+
dagger1
0
15
15
0
0
3
10
1

1
Dagger++
dagger2
0
20
20
0
0
3
15
1

1
Axe
axe
0
20
0
0
0
3
1
1

1
Legs
femalelegs
0
10
10
10
0
5
1
1

1
Boots
boots
0
5
5
5
0
6
1
1

1
Boots+
boots1
0
7
7
7
0
6
10
1

1
Boots++
boots2
0
15
15
15
0
6
15
1

1
HP Potion
hppot
1
0
0
0
20
0
1
1


//fim
</details>
