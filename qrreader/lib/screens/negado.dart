import 'package:flutter/material.dart';
import 'package:mobile_scanner/mobile_scanner.dart';

class negado extends StatelessWidget {
  const negado({super.key, required this.barcode});

  final Barcode barcode;

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Text('Negado: barcode.rawValue!'),
    );
  }
}