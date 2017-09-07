## Definição

O trabalho consiste em criar uma aplicação Cliente/Servidor para que seja possível que os clientes joguem o jogo da Forca Gaudéria.

## Servidor

O servidor deverá:

Carregar um arquivo contendo palavras e dicas
Aguardar requisições dos clientes na porta 8765
Tratar as requisições de acordo com o protocolo definido abaixo

## Cliente

O cliente deverá:

1. Solicitar ao usuário o endereço IP e a porta do Servidor ao qual ele deve se conectar
2. Deve solicitar também o nome do usuário e chave.
3. Conectar ao servidor
4. Solicitar uma palavra/frase ao servidor usando o protocolo de comunicação
5. Montar os espaços da palavra/frase para que o usuário tente adivinhar
6. Solicitar ao usuário uma letra de cada vez
7. Caso a letra esteja na palavra, revelar a letra na posição correspondente. Caso não esteja, contabilizar um erro.
8. O usuário pode errar quatro vezes. No quinto erro o usuário perde o jogo e a palavra é revelada.
9. Quando o usuário acertar metade das letras da palavra/frase deve ser exibida a dica correspondente.
10. Ao término do jogo, deve ser perguntado ao usuário se ele deseja continuar. Caso continue, o ciclo é repetido a partir da solicitação da palavra ao servidor. Caso não continue, o cliente deve informar o servidor o nome do usuário, a quantidade de jogos que ele ganhou e a quantidade das que ele perdeu. deve ser exibido o ranking de usuários, a conexão ao servidor é encerrada e o programa finalizado.
11. Prococolo de Comunicação
Buscar palavra/frase
Cliente envia comando BUSCARPALAVRA. O servidor escolhe uma palavra da lista de forma randômica e devolve para o cliente em formato JSON.

*Requisição:*

**BUSCARPALAVRA**

*Resposta:*

```json
{
	"palavra" : "Bah",
	"dica" : "Interjeição que vale para quase tudo, dependendo da entonação, pode ser usado como surpresa, rejeição, aprovação, admiração."
}
```

### Encerrar Jogo
Cliente envia comando ENCERRARJOGO juntamente com o usuário, chave, quantidade de vitórias e derrotas. O servidor verifica se o usuário/chave já está cadastrado. Caso esteja, soma a quantidade de vitórias/derrotas informadas. Caso não esteja, cria o registro correspondente. Importante: Estes dados devem ser persistidos em arquivo. O servidor deve responder um JSON com o total de vitórias/derrotas do usuário (já somadas com as que tinha anteriormente)

*Requisição:*

**ENCERRARJOGO tales chavetales 10 2**

*Resposta:*

```json
{
	"usuario" : "tales",
	"vitorias" : 150,
	"derrotas" : 10
}
```

### Buscar Ranking
Cliente envia comando BUSCARRANKING. O servidor retorna o ranking com todos os usuário cadastrados no sistema, ordenados decrescentemente pelo percentual de vitórias do usuário.

*Requisição:*

**BUSCARRANKING**

*Resposta:*

```json
{
	"ranking" : [
		{
			"usuario" : "tales",
			"vitorias" : 150,
			"derrotas" : 10,
			"percentual" : 93.75
		},
		{
			"usuario" : "jones",
			"vitorias" : 5,
			"derrotas" : 5,
			"percentual" :  50.0
		}
	]
}
```

### Importante:

1. O servidor deve poder funcionar com qualquer cliente que saiba conversar o protocolo definido acima.
2. O cliente deve poder funcionar com qualquer servidor que saiba conversar o protocolo definido acima.
3. O servidor deverá ser capaz de aceitar requisições de múltiplos clientes ao mesmo tempo.
4. Todo protocolo é em modo texto.
5. O trabalho deve ser apresentado para o professor na sala de aula na data indicada. Caso o aluno não esteja presente para fazer a apresentação, será penalizado com metade da nota.
6. O trabalho pode ser em duplas
