# MC855 Carteirinha-Estudantil
Projeto da disciplina MC855 do Instituto de Computação da Universidade Estadual de Campinas no segundo semestre de 2022

# Membros do Projeto

- David Afonso 
- Alan Freitas
- Renan Hiroki
- Leandro Garcia

# Descrição do projeto

Durante a disciplina os alunos se propuseram a desenvolver um aplicativo de identidade digital para ser usado pelos membros da comunidade da Universidade Estadual de Campinas (UNICAMP). O resultado final foi um produto viável mínimo (MVP na sigla em inglês) com uma primeira versão das seguintes funcionalidades implementadas:

- O aplicativao realiza o login dos usuários cadastrados no banco de dados a partir das informações RA (matrícula da Unicamp) e da senha.
- O aplicativo consegue gerar códigos PIX com valores fornecidos pelo usuário a partir da API do Banco do Brasil
- O aplicativo gera um QR Code contendo a matrícula do aluno logado no aplicativo e um token para permitir o pagamento de refeições no restaurante universitário
- O aplicativo tem uma interface para a geração de notificações no dispositivo do usuário que, com um refinamento, pode ser usada para gerar notificações quando o saldo de créditos na conta do usuário estiver muito baixo.

No futuro uma integração entre o aplicativo descrito acima e o sistema da Unicamp pode ser estudada.

# Estrutura do projeto

O projeto está dividido em duas partes principais:

- [FrontEnd](https://github.com/Necctares/MC855---Carteirinha-Estudantil/tree/main/projeto)
- Backend

Houve a tentativa de implementação de um código de leitor de QR Code para ser usado em testes com os usuários. O código desenvolvido está disponível em [Código leitor de QR Code](https://github.com/Necctares/MC855---Carteirinha-Estudantil/tree/main/qrreader)

# Considerações finais

Caso haja um interesse de se implementar o código desenvolvido na Unicamp seria necessário uma discussão com membros da administração e um estudo do melhor modo de realizar essa integração.
