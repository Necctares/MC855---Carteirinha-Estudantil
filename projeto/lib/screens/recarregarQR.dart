import 'dart:async';
import 'package:qr_flutter/qr_flutter.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class recarregarQR extends StatefulWidget {
  const recarregarQR({Key? key}) : super(key: key);

  @override
  State<recarregarQR> createState() => _recarregarQRState();
}

class _recarregarQRState extends State<recarregarQR>
    with TickerProviderStateMixin {
  var codigoPix = '00020101021226770014BR.GOV.BCB.P...';
  Duration timerDuration = Duration(minutes: 5);
  late AnimationController controller;

  @override
  void initState() {
    controller = AnimationController(
      vsync: this,
      duration: const Duration(minutes: 5),
    )..addListener(() {
        setState(() {});
      });
    controller.reverse(from: 1);
    Timer timer = Timer.periodic(Duration(seconds: 1), (timer) {
      if (timerDuration.inSeconds > 0)
        timerDuration = Duration(seconds: timerDuration.inSeconds - 1);
      else
        timer.cancel();
    });
    super.initState();
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  format(Duration d) => d.toString().split('.').first.padLeft(8, "0");

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
            QrImage(
              data: codigoPix,
              version: QrVersions.auto,
              size: 300.0,
            ),
            SizedBox(
              height: 25,
            ),
            SizedBox(
              width: 900,
              child: TextField(
                readOnly: true,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  hintText: codigoPix,
                ),
              ),
            ),
            SizedBox(
              height: 10,
            ),
            Text(
              'Tempo para pagar: ${format(timerDuration)}',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 15,
              ),
            ),
            SizedBox(
              height: 10,
            ),
            Transform.scale(
              scaleX: 0.5,
              child: LinearProgressIndicator(
                value: controller.value,
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
                    onPressed: () async {
                      await Clipboard.setData(ClipboardData(text: codigoPix));
                    },
                    child: Text('Copiar QR Code'),
                  ),
                  ElevatedButton(onPressed: () {}, child: Text('Como funciona'))
                ]),
          ],
        ),
      ),
    );
  }
}
