import 'package:flutter/material.dart';
import 'package:untitled/screens/mainMenu.dart';

class fotoCarteirinha extends StatefulWidget {
  const fotoCarteirinha({Key? key}) : super(key: key);

  @override
  State<fotoCarteirinha> createState() => _fotoCarteirinhaState();
}

class _fotoCarteirinhaState extends State<fotoCarteirinha> {

  void _voltarMenu() {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
        builder: (context) {

          return mainMenu();
        },
      ),
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
              height: 25,
            ),
            SizedBox(
              height: 500,
              width: 500,
            child: Image.asset('assets/images/carteirinha.jpeg')
            ),
            SizedBox(
              height: 25,
            ),
            ElevatedButton(onPressed: _voltarMenu, child: Text('Retornar à tela inicial')),
          ],
        ),
      ),
    );
  }
}
