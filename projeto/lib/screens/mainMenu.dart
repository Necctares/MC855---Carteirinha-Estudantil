import 'package:flutter/material.dart';
import 'menuLateral.dart';
import 'carteirinha.dart';
import 'recarregarValor.dart';
import 'pagamentoQR.dart';
import 'usuarioInfo.dart';

class mainMenu extends StatefulWidget {
  const mainMenu({
    super.key,
    /*required this.inform*/
  });

  //final usuarioInfo inform;

  @override
  State<mainMenu> createState() => _mainMenuState();
}

class _mainMenuState extends State<mainMenu> {
  void _autentificar() {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
        builder: (context) {
          return carteirinha();
        },
      ),
    );
  }

  void _recarregar() {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
        builder: (context) {
          return recarregarValor();
        },
      ),
    );
  }

  void _pagar() {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
        builder: (context) {
          return pagamentoQR();
        },
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: menuLateral(),
      appBar: AppBar(
        actions: [
          Image.asset('assets/images/logounicamp.png'),
        ],
      ),
      body: SizedBox(
        width: double.infinity,
        child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              CircleAvatar(
                foregroundImage: AssetImage('assets/images/fotoperfil.jpeg'),
                radius: 100,
              ),
              SizedBox(
                height: 10,
              ),
              Wrap(
                alignment: WrapAlignment.center,
                direction: Axis.vertical,
                spacing: 10,
                children: <Widget>[
                  Text('Nome: ' + 'Renan Hiroki'),
                  Text('Matrícula: 123456'),
                  Text('Crédito: R\$ 8,00')
                ],
              ),
              SizedBox(
                height: 10,
              ),
              Wrap(
                  alignment: WrapAlignment.center,
                  spacing: 25,
                  direction: Axis.horizontal,
                  children: <Widget>[
                    ElevatedButton(
                      onPressed: _autentificar,
                      child: Text('Carteirinha Digital'),
                    ),
                    ElevatedButton(
                        onPressed: _recarregar, child: Text('Adicionar saldo'))
                  ]),
              SizedBox(
                height: 25,
              ),
              Wrap(
                alignment: WrapAlignment.spaceBetween,
                children: <Widget>[
                  ElevatedButton(
                    onPressed: _pagar,
                    child: Text('Realizar Pagamento'),
                  ),
                ],
              ),
            ]),
      ),
    );
  }
}
