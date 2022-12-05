# FrontEnd

Código do FrontEnd desenvolvido durante o projeto.

## Primeiros Passos

O código disponível nesse repositório foi desenvolvido em Flutter uma vez que os alunos possuiam certa familiaridade com a linguagem e ela possiblita desenvolvimento de código funcional em iOS, Android e Web.

Instruções para instalar Flutter no sistema operacional Windows estão disponíveis no link abaixo.
[Instalar Flutter](https://docs.flutter.dev/get-started/install/windows)

O código foi desenvolvido usando a IDE Android Studio que permite tanto o teste local quanto o teste em dispositivos móveis (Android no contexto geral, iOS caso se tenha disponível um computador com sistema operacional MacOS).

Instruções para configurar o Android Studio estão disponíveis no link abaixo.
[Configurar Android Studio](https://docs.flutter.dev/get-started/editor?tab=androidstudio)

## Estrutura do FrontEnd

A estrutura do FrontEnd está ilustrada na imagem abaixo.

![Diagrama do FrontEnd](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/projeto/MC855entrega6.png)

## Telas do aplicativo

Segue uma lista das telas criadas para o aplicativo, com descrição dos elementos presentes, funcionalidade e link para o código disponível nesse repositório.

1. Tela inicial
   * Descrição
        - Funcionalidades: A tela inicial realiza o login do usuário, recebe como entrada a matrícula do aluno e a senha e, envia tais informações para o servidor. Permite ao usuário lembrar sua matrícula para usos futuros. Caso as informações sejam válidas, passa para o Menu Principal, caso contrário, mostra um aviso para o usuário inserir outras informações
        - Elementos presentes: A tela inicial tem dois campos de entrada de texto, uma checkbox para caso o usuário deseje guardar sua matrícula para usos posteriores e um botão que envia as informações para o servidor e realiza a mudança de página
   * [Código Tela inicial](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/projeto/lib/screens/telaInicial.dart)
2. Menu Principal
   * Descrição
        - Funcionalidades: O Menu principal mostra as informações mais relevantes da conta do usuário (nome, matrícula, saldo e foto de perfil), e permite a navegação para as demais telas.
        - Elementos presentes: O Menu principal tem uma barra lateral que disponibiliza informações do usuário e permite a navegação para as telas de configuração e de perguntas frequentes, um botão que permite a navegação para a tela de carteira digital, um botão que permite a navegação para a tela de qr code de pagamento do restaurante e um botão que permite a navegação para a tela onde é realizada a entrada do valor que será recarregado via pix.
   * [Código Menu Principal](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/projeto/lib/screens/mainMenu.dart)
3. QR Code carteirinha
   * Descrição
        - Funcionalidades: A tela QR Code carteirinha mostra um QR Code que pode ser usado para identificação do aluno. A geração do QR Code já ocorre, porém a informação que será usada para identificar o aluno precisa ser discutida ainda com a Unicamp.
        - Elementos presentes: Possui a imagem do QR Code e um botão para retornar ao Menu Principal
   * [Código QR Code carteirinha](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/projeto/lib/screens/carteirinha.dart)
4. Foto Carteirinha
   * Descrição
        - Funcionalidades: A tela Foto carteirinha mostra uma foto da carteirinha do aluno. Não temos acesso a fotos de carteirinha de alunos, por isso está mostrando uma foto padrão. A implementação dessa funcionalidade precisa ser discutida ainda com a Unicamp
        - Elementos presentes: Possui a imagem da carteirinha do aluno e um botão para retornar ao Menu Principal
   * [Código Foto carteirinha](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/projeto/lib/screens/fotoCarteirinha.dart)
5. QR Code pagamento
   * Descrição
        - Funcionalidades: A tela QR Code pagamento mostra um qr code que será usado para identificar o aluno no momento da entrada do restaurante. No momento o QR Code contém a matrícula do aluno e um token recebido do servidor no momento do login.
        - Elementos presentes: Possui a imagem do QR Code e um botão para retornar ao Menu Principal
   * [Código QR Code pagamento](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/projeto/lib/screens/pagamentoQR.dart)
6. Selecionar Valor
   * Descrição
        - Funcionalidades: A tela selecionar valor permite ao usuário inserir o valor que deseja adicionar ao seu saldo, esse valor é usado para gerar o código pix por meio da api do banco do brasil.
        - Elementos presentes: Possui um campo de texto para a entrada do valor a ser recarregado e um botão para enviar tais informações para a API.
   * [Código Selecionar valor](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/projeto/lib/screens/recarregarValor.dart)
7. QR Code Pix
   * Descrição
        - Funcionalidades: A tela QR Code pix disponibiliza ao usuário o código pix gerado pela API do Banco do Brasil.
        - Elementos presentes: Possui um texto indicando o status do pagamento (ainda precisa ser desenvolvido essa funcionalidade), uma imagem do QR Code contendo o código pix, uma caixa de texto com o código pix, um contador e uma barra de progresso indicando o tempo disponível para realizar o pagamento, um botão para copiar o código pix para a área de transferência e um botão para conferir o status do pagamento (essa funcionalidade ainda precisa ser implementada).
   * [Código QR Code Pix](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/projeto/lib/screens/recarregarQR.dart)
8. Configurações
   * Descrição
        - Funcionalidades:
        - Elementos presentes:
   * [Código Configurações](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/projeto/lib/screens/notifications.dart)
9. Perguntas frequentes
   * Descrição
        - Funcionalidades: A tela perguntas frequentes disponibiliza informações que podem ser úteis ao usuário quanto as funcionalidades do aplicativo, no momento contém apenas texto estático, ainda precisa ser implementada
        - Elementos presentes: lista com perguntas frequentemente feitas pelos usuários e as respectivas respostas.
   * [Código Perguntas frequentes](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/projeto/lib/screens/fAQ.dart)
