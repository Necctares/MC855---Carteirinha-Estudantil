import 'package:flutter/material.dart';
import 'package:qr_flutter/qr_flutter.dart';
import 'package:untitled/screens/credentials.dart';
import 'package:untitled/screens/restaurant.dart';
import 'package:untitled/screens/usuarioInfo.dart';

class pagamentoQR extends StatelessWidget {
  pagamentoQR(
      {super.key,
      required this.inform,
      required this.rest,
      required this.credential});

  final usuarioInfo inform;
  final restaurant rest;
  final credentials credential;

  @override
  Widget build(BuildContext context) {
    void _voltarMenu() {
      Navigator.of(context).popUntil((route) => route.isFirst);
    }

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
              'Por favor, aproxime o celular do leitor de QR Code',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 25,
              ),
            ),
            SizedBox(
              height: 75,
            ),
            QrImage(
              data: inform.matricula.toString(),
              version: QrVersions.auto,
              size: 300.0,
            ),
            SizedBox(
              height: 80,
            ),
            ElevatedButton(
                onPressed: _voltarMenu, child: Text('Retornar à tela inicial')),
          ],
        ),
      ),
    );
    ;
  }
}
