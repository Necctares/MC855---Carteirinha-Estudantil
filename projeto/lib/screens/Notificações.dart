import 'package:flutter/material.dart';

class Notificacoes extends StatelessWidget {
  const Notificacoes({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: [
          Image.asset('assets/images/logounicamp.png'),
        ],
      ),
      body: ListView(
        children: <Widget>[
          SizedBox(
            height: 50,
          ),
          Container(
            alignment: Alignment.center,
            child: Text(
              'Notificações',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 40,
              ),
            ),
          ),
          Divider(),
          ListTile(
            leading: Icon(Icons.wallet),
            title: Text(
              'Saldo',
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            subtitle:
                Text('Seu saldo é menor que R\$10,00\nSaldo atual: R\$8,00'),
            trailing: Text(
              'Hoje às 13:31',
              style: TextStyle(
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
          Divider(),
        ],
      ),
    );
  }
}
