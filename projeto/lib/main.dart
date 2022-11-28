// Copyright 2018 The Flutter team. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

import 'package:flutter/material.dart';
import 'screens/mainMenu.dart';
import 'screens/carteirinha.dart';
import 'screens/telaInicial.dart';
import 'package:provider/provider.dart';
import 'package:screens/notification_service.dart';

void main() {
  runApp(
    MultiProvider(
      providers: [
        Provider<NotificationService>(create: (context) => NotificationService()),
      ],
      child: const MyApp(),
    )
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'CarteirinhaUnicamp',
      theme: ThemeData(
        appBarTheme: const AppBarTheme(
          backgroundColor: Color(0xFFB71C1C),
          foregroundColor: Colors.black
        ),
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            textStyle: TextStyle(fontSize: 25, fontWeight: FontWeight.bold),
            backgroundColor: Color(0xFFB71C1C),
            foregroundColor: Colors.black
          )
        ),
      ),
      home: telaInicial(),
    );
  }
}


