import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:mobile_scanner/mobile_scanner.dart';
import 'screens/autorizado.dart';
import 'screens/negado.dart';
import 'screens/restaurant.dart';

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

  get http => null;

  @override
  Widget build(BuildContext context) {
    String code = 'Hello';

    Future<int> _validBarCode(Barcode barcode) async {
      String qrCodeString = barcode.rawValue!;
      String ra = qrCodeString.substring(0, 5);
      String acessToken = qrCodeString.substring(6);
      restaurant rest = await _getRestaurant(int.parse(ra), acessToken);
      if (rest.already_ate == 1) {
        return 0;
      } else if (rest.credits < 3.00) {
        return 0;
      } else {
        _debitStudent(int.parse(ra));
        return 1;
      }
    }

    return MobileScanner(
        allowDuplicates: false,
        onDetect: (barcode, args) {
          if (_validBarCode(barcode) == 1) {
            Navigator.of(context).push(
              MaterialPageRoute<void>(
                builder: (context) {
                  return autorizado(
                    barcode: barcode,
                  );
                },
              ),
            );
          } else {
            Navigator.of(context).push(
              MaterialPageRoute<void>(
                builder: (context) {
                  return negado(
                    barcode: barcode,
                  );
                },
              ),
            );
          }
        });
  }

  Future<restaurant> _getRestaurant(int ra, String acessToken) async {
    final response = await http.post(
        Uri.parse('https://carteirinhadigital-364020.rj.r.appspot.com/ru'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode({
          "ra": ra,
          "key": acessToken,
        }));

    if (response.statusCode == 200) {
      return restaurant.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Bad Connection.');
    }
  }

  void _debitStudent(int ra) async {
    final response = await http.put(
        Uri.parse(
            'https://carteirinhadigital-364020.rj.r.appspot.com/ru/eated'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode({
          "ra": ra,
          "key":
              "C7784AFD92DE2CE4C00CFB887E3FA1B13D7535FFE70EC8C6B95F63A6AC064EFC",
          "id": 0
        }));

    if (response.statusCode != 200) {
      throw Exception('Bad Connection.');
    }
  }
}
