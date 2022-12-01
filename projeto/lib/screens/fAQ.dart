import 'package:flutter/material.dart';
import 'package:untitled/screens/menuLateral.dart';
import 'usuarioInfo.dart';

class fAQ extends StatelessWidget {
  const fAQ({
    super.key,
    required this.inform,
});

  final usuarioInfo inform;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: menuLateral(inform: inform,),
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
              'Perguntas Frequentes',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 40,
              ),
            ),
          ),
          Divider(),
          ListTile(
            title: Text(
              'Qual valor mínimo eu posso inserir?',
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            subtitle:
            Text('Não há valor mínimo'),
          ),
          Divider(),
        ],
      ),
    );;
  }
}
