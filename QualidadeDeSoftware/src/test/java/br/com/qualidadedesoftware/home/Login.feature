# language: pt

Funcionalidade: Login

	@Monitoração
	Cenário: Realizar login de usuário com sucesso
		Dado que abro a home do walmart
	    E clico no botão [Entre]
	    Quando preencho os dados de login válidos
	    Então valido o login realizado com sucesso
	    E faço o logoff

	@Regressão
  	Cenário: Tento realizar login de usuário com dados inválidos
	    Dado que abro a home do walmart
	    E clico no botão [Entre]
	    Quando preencho os dados de login inválidos
	    Então valido a mensagem de dados inválidos
    
