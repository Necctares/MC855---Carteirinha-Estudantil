import 'package:flutter/material.dart';

class recarregarQR extends StatefulWidget {
  const recarregarQR({Key? key}) : super(key: key);

  @override
  State<recarregarQR> createState() => _recarregarQRState();
}

class _recarregarQRState extends State<recarregarQR> {
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
              height: 25,
            ),
            Text(
              'Status do pagamento: Aguardando',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 15,
              ),
            ),
            SizedBox(
              height: 25,
            ),
            Image.asset('assets/images/qrcode.png'),
            SizedBox(
              height: 25,
            ),
            TextField(
              readOnly: true,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: '00020101021226770014BR.GOV.BCB.P...',
              ),
            ),
            SizedBox(
              height: 10,
            ),
            Text(
              'tempo para pagar: 3:23',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 15,
              ),
            ),
        SizedBox(
            height: 50,
        ),
        Wrap(
            alignment: WrapAlignment.center,
            spacing: 50,
            direction: Axis.horizontal,
            children: <Widget>[
              ElevatedButton(
                onPressed: () {},
                child: Text('Copiar QR Code'),
              ),
              ElevatedButton(
                  onPressed: () {},
                  child: Text('Como funciona')
              )
            ]
        ),
              ],
        ),
      ),
    );
  }
}
