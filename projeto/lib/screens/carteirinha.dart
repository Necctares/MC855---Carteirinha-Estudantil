import 'package:flutter/material.dart';
import 'package:untitled/screens/mainMenu.dart';
import 'fotoCarteirinha.dart';

class carteirinha extends StatefulWidget {
  const carteirinha({super.key});

  @override
  State<carteirinha> createState() => _carteirinhaState();
}

class _carteirinhaState extends State<carteirinha> {

  void _voltarMenu() {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
        builder: (context) {

          return mainMenu();
        },
      ),
    );
  }

  void _visualizarCarteirinha() {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
        builder: (context) {

          return fotoCarteirinha();
        }
      )
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: SizedBox(
          height: 100,
          width: 100,
          child: IconButton(
            icon: const Icon(Icons.list),
            onPressed: () {},
            tooltip: 'Menu lateral',
          ),
        ),
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
            Image.asset('assets/images/qrcode.png'),
            SizedBox(
              height: 75,
            ),
            ElevatedButton(onPressed: _visualizarCarteirinha, child: Text('Visualizar carteirinha')),
            SizedBox(
              height: 20,
            ),
            ElevatedButton(onPressed: _voltarMenu, child: Text('Retornar à tela inicial')),
          ],
        ),
      ),
    );
  }
}