import 'package:flutter/material.dart';
import 'package:mobile_scanner/mobile_scanner.dart';
import 'screens/autorizado.dart';
import 'screens/negado.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  const MyHomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    String code = 'Hello';

    int _validBarCode(Barcode barcode) {
      return 1;
    }

    return MobileScanner(
        allowDuplicates: false,
        onDetect: (barcode, args) {
          if (_validBarCode(barcode) == 1) {
            Navigator.of(context).push(
              MaterialPageRoute<void>(
                builder: (context) {
                  return autorizado(barcode: barcode,);
                },
              ),
            );
          }
          else {
            Navigator.of(context).push(
              MaterialPageRoute<void>(
                builder: (context) {
                  return negado(barcode: barcode,);
                },
              ),
            );
          }
        });

  }
}
