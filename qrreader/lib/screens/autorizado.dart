import 'package:flutter/material.dart';
import 'package:mobile_scanner/mobile_scanner.dart';

class autorizado extends StatelessWidget {
  const autorizado({super.key, required this.barcode});

  final Barcode barcode;

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Text(barcode.rawValue!),
    );
  }
}
