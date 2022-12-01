import 'dart:convert';
import 'dart:async';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:untitled/screens/credentials.dart';
import 'package:untitled/screens/restaurant.dart';
import 'usuarioInfo.dart';
import 'mainMenu.dart';
import 'credentials.dart';
import 'package:http/http.dart' as http;

class telaInicial extends StatefulWidget {
  const telaInicial({Key? key}) : super(key: key);

  @override
  State<telaInicial> createState() => _telaInicialState();
}

class _telaInicialState extends State<telaInicial> {
  final raController = TextEditingController();
  final passwordController = TextEditingController();
  bool rememberRA = false;
  String ra = '';
  String? password;
  credentials? credential;
  usuarioInfo? user;
  restaurant? rest;
  // final user = usuarioInfo(
  //     nome: "David",
  //     matricula: 261032,
  //     cpf: '70140422110',
  //     curso: 'Engenharia de Computacao',
  //     data_de_expiracao: '2023-01-01',
  //     url: 'https://dac.unicamp.br/pics/123456');

  @override
  void initState() {
    super.initState();
    _getRA();
  }

  @override
  void dispose() {
    raController.dispose();
    passwordController.dispose();
    super.dispose();
  }

  void _logar() async {
    _saveRA();
    credential = await _logarUsuario();
    if (credential!.response == "success") {
      user = await _getUserInfo(credential!);
      rest = await _getRestaurant(credential!);
      Navigator.of(context).pushAndRemoveUntil(
        MaterialPageRoute<void>(
          builder: (context) {
            return mainMenu(
              inform: user!,
              rest: rest!,
              credential: credential!,
            );
          },
        ),
        (route) => false,
      );
    } else {
      showDialog(
          context: context,
          builder: (_) => AlertDialog(
                title: Text("Erro"),
                content: Text("Login Inválido"),
                actions: [
                  ElevatedButton(
                      onPressed: () {
                        Navigator.of(context).pop();
                      },
                      child: Text("Descartar"))
                ],
              ));
    }
  }

  Future _getRA() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      rememberRA = prefs.getBool('rememberEmail') ?? false;
      ra = prefs.getString('email') ?? '';
      if (rememberRA == true) {
        raController.text = ra;
      }
    });
  }

  Future _saveRA() async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setBool('rememberEmail', rememberRA);
    if (rememberRA) {
      prefs.setString('email', raController.text);
    } else {
      prefs.remove('email');
    }
  }

  Future<credentials> _logarUsuario() async {
    final response = await http.post(
        Uri.parse('https://carteirinhadigital-364020.rj.r.appspot.com/login'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode({
          "ra": raController.text,
          "password": passwordController.text,
        }));

    if (response.statusCode == 200) {
      return credentials.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Bad Connection.');
    }
  }

  Future<usuarioInfo> _getUserInfo(credentials cred) async {
    final response = await http.post(
        Uri.parse(
            'https://carteirinhadigital-364020.rj.r.appspot.com/student/byId'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode({
          "ra": raController.text,
          "key": cred.accessToken,
        }));

    if (response.statusCode == 200) {
      return usuarioInfo.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Bad Connection.');
    }
  }

  Future<restaurant> _getRestaurant(credentials cred) async {
    final response = await http.post(
        Uri.parse('https://carteirinhadigital-364020.rj.r.appspot.com/ru'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode({
          "ra": raController.text,
          "key": cred.accessToken,
        }));

    if (response.statusCode == 200) {
      return restaurant.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to login.');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: [],
      ),
      body: SizedBox(
        width: double.infinity,
        child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Image.asset('assets/images/logounicamp.png'),
              SizedBox(
                height: 100,
              ),
              Transform.scale(
                scaleX: 0.9,
                child: TextField(
                  decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    hintText: 'RA',
                  ),
                  controller: raController,
                ),
              ),
              SizedBox(
                height: 50,
              ),
              Transform.scale(
                scaleX: 0.9,
                child: TextField(
                  obscureText: true,
                  decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    hintText: 'Senha',
                  ),
                  controller: passwordController,
                ),
              ),
              SizedBox(
                height: 20,
              ),
              Row(
                children: [
                  SizedBox(
                    width: 45,
                  ),
                  Checkbox(
                    value: rememberRA,
                    onChanged: (bool? value) {
                      setState(() {
                        rememberRA = value!;
                      });
                    },
                  ),
                  Text('Lembrar RA')
                ],
              ),
              SizedBox(
                height: 50,
              ),
              Wrap(
                alignment: WrapAlignment.spaceBetween,
                children: <Widget>[
                  ElevatedButton(
                    onPressed: _logar,
                    child: Text('Entrar'),
                  ),
                ],
              ),
            ]),
      ),
    );
  }
}
