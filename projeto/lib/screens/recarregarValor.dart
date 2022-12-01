import 'package:flutter/material.dart';
import 'recarregarQR.dart';
import 'usuarioInfo.dart';

class recarregarValor extends StatelessWidget {
  recarregarValor({
    super.key,
    required this.inform,
  });

  final usuarioInfo inform;
  final valorController = TextEditingController();

  @override
  Widget build(BuildContext context) {

    void _recarregar() {
      Navigator.of(context).push(
        MaterialPageRoute<void>(
          builder: (context) {
            return recarregarQR(value: valorController.text,inform: inform,);
          },
        ),
      );
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
              'Recarregar com PIX',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 25,
              ),
            ),
            SizedBox(
              height: 200,
            ),
            Transform.scale(
              scaleX: 0.9,
              child: TextField(
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  hintText: 'Valor',
                ),
                controller: valorController,
              ),
            ),
            SizedBox(
              height: 200,
            ),
            ElevatedButton(
                onPressed: _recarregar, child: Text('Gerar QR Code')),
          ],
        ),
      ),
    );
  }
}
