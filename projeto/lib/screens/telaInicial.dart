import 'package:flutter/material.dart';
import 'mainMenu.dart';
import 'usuarioInfo.dart';

class telaInicial extends StatefulWidget {
  const telaInicial({Key? key}) : super(key: key);

  @override
  State<telaInicial> createState() => _telaInicialState();
}

class _telaInicialState extends State<telaInicial> {
  void _logar() {
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
        actions: [],
      ),
      body: SizedBox(
        width: double.infinity,
        child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Image.asset('assets/images/logounicamp.png'),
              SizedBox(
                height: 100,
              ),
              Transform.scale(
                scaleX: 0.9,
                child: TextField(
                  decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    hintText: 'E-mail',
                  ),
                ),
              ),
              SizedBox(
                height: 50,
              ),
              Transform.scale(
                scaleX: 0.9,
                child: TextField(
                  obscureText: true,
                  decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    hintText: 'Senha',
                  ),
                ),
              ),
              SizedBox(
                height: 50,
              ),
              Wrap(
                alignment: WrapAlignment.spaceBetween,
                children: <Widget>[
                  ElevatedButton(
                    onPressed: _logar,
                    child: Text('Entrar'),
                  ),
                ],
              ),
            ]),
      ),
    );
  }
}
