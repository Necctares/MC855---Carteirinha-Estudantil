import 'package:flutter/material.dart';
import 'package:untitled/screens/mainMenu.dart';
import 'package:untitled/screens/menuLateral.dart';
import 'fotoCarteirinha.dart';
import 'package:qr_flutter/qr_flutter.dart';

class carteirinha extends StatefulWidget {
  const carteirinha({super.key});

  @override
  State<carteirinha> createState() => _carteirinhaState();
}

class _carteirinhaState extends State<carteirinha> {
  var userIdLink = "https://www.youtube.com/watch?v=BvalYFjApoY";

  void _voltarMenu() {
    Navigator.of(context).popUntil((route) => route.isFirst);
  }

  void _visualizarCarteirinha() {
    Navigator.of(context).push(MaterialPageRoute<void>(builder: (context) {
      return fotoCarteirinha();
    }));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: [
          Image.asset('assets/images/logounicamp.png'),
        ],
      ),
      body: SizedBox(
        width: double.infinity,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            SizedBox(
              height: 50,
            ),
            Text(
              'Autentificação de carteirinha',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 25,
              ),
            ),
            SizedBox(
              height: 75,
            ),
            QrImage(
              data: userIdLink,
              version: QrVersions.auto,
              size: 300.0,
            ),
            SizedBox(
              height: 75,
            ),
            ElevatedButton(
                onPressed: _visualizarCarteirinha,
                child: Text('Visualizar carteirinha')),
            SizedBox(
              height: 20,
            ),
            ElevatedButton(
                onPressed: _voltarMenu, child: Text('Retornar à tela inicial')),
          ],
        ),
      ),
    );
  }
}
