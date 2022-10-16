import 'package:flutter/material.dart';
import 'recarregarQR.dart';

class recarregarValor extends StatefulWidget {
  const recarregarValor({Key? key}) : super(key: key);

  @override
  State<recarregarValor> createState() => _recarregarValorState();
}

class _recarregarValorState extends State<recarregarValor> {

  void _recarregar() {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
        builder: (context) {

          return recarregarQR();
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
            TextField(
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: 'valor',
              ),
            ),
            SizedBox(
              height: 200,
            ),
            ElevatedButton(onPressed: _recarregar, child: Text('Gerar QR Code')),
          ],
        ),
      ),
    );
  }
}
