import 'package:flutter/material.dart';

class pagamentoQR extends StatefulWidget {
  const pagamentoQR({Key? key}) : super(key: key);

  @override
  State<pagamentoQR> createState() => _pagamentoQRState();
}

class _pagamentoQRState extends State<pagamentoQR> {
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
              'Por favor, aproxime o celular do leitor de QR Code',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 25,
              ),
            ),
            SizedBox(
              height: 75,
            ),
            Image.asset('assets/images/qrcode.png'),
          ],
        ),
      ),
    );
    ;
  }
}
