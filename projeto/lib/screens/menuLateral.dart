import 'package:flutter/material.dart';
import 'Notificações.dart';
import 'fAQ.dart';
import 'package:untitled/screens/mainMenu.dart';

class menuLateral extends StatelessWidget {
  const menuLateral({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(
      backgroundColor: Colors.grey.shade300,
      child: Container(
        height: double.infinity,
        child: Column(
          children: <Widget>[
            Container(
              width: double.infinity,
              child: UserAccountsDrawerHeader(
                accountEmail: Text('r123456@dac.unicamp.br'),
                accountName: Text('Renan'),
                currentAccountPicture: CircleAvatar(
                  foregroundImage: AssetImage('assets/images/fotoperfil.jpeg'),
                ),
                decoration: BoxDecoration(
                  color: Color(0xFFB71C1C),
                ),
              ),
            ),
            ListTile(
              title: Text('Menu Principal'),
              hoverColor: Colors.blue,
              onTap: () {
                Navigator.of(context).popUntil((route) => route.isFirst);
              },
            ),
            ListTile(
              title: Text('Notificações'),
              hoverColor: Colors.blue,
              onTap: () {
                Navigator.of(context).push(
                  MaterialPageRoute<void>(
                    builder: (context) {
                      return Notificacoes();
                    },
                  ),
                );
              },
            ),
            ListTile(
              title: Text('Configurações'),
              hoverColor: Colors.blue,
              onTap: () {},
            ),
            ListTile(
              title: Text('Perguntas Frequentes'),
              hoverColor: Colors.blue,
              onTap: () {
                Navigator.of(context).push(
                  MaterialPageRoute<void>(
                    builder: (context) {
                      return fAQ();
                    },
                  ),
                );
              },
            ),
            ListTile(
              title: Text('Primeiro acesso ?'),
            ),
            ListTile(
              title: Text('Entre em contato'),
            ),
            Spacer(),
            Align(
              alignment: FractionalOffset.bottomCenter,
              child: ListTile(
                title: Text('\u00a9 Grupo Identidade Digital Unicamp'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
