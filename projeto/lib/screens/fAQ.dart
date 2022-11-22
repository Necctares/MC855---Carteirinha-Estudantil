import 'package:flutter/material.dart';
import 'package:untitled/screens/menuLateral.dart';

class fAQ extends StatelessWidget {
  const fAQ({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: menuLateral(),
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
              'Qual valor mínimo eu posso inserir ?',
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
