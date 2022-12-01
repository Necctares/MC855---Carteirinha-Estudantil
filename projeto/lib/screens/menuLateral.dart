import 'package:flutter/material.dart';
import 'fAQ.dart';
import 'usuarioInfo.dart';
import 'notifications.dart';

class menuLateral extends StatelessWidget {
  const menuLateral({
    super.key,
    required this.inform,
});

  final usuarioInfo inform;

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
                accountEmail: Text(inform.nome[0].toString().toLowerCase() + inform.matricula.toString() + '@dac.unicamp.br'),
                accountName: Text(inform.nome),
                currentAccountPicture: CircleAvatar(
                  foregroundImage: NetworkImage(inform.url),
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
              title: Text('Configurações'),
              hoverColor: Colors.blue,
              onTap: () {
                Navigator.of(context).push(
                  MaterialPageRoute<void>(
                    builder: (context) {
                      return confignot(inform: inform,);
                    },
                  ),
                );
              },
            ),
            ListTile(
              title: Text('Perguntas Frequentes'),
              hoverColor: Colors.blue,
              onTap: () {
                Navigator.of(context).push(
                  MaterialPageRoute<void>(
                    builder: (context) {
                      return fAQ(inform: inform,);
                    },
                  ),
                );
              },
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
