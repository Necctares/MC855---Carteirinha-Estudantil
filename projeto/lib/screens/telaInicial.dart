import 'dart:convert';
import 'dart:async';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'usuarioInfo.dart';
import 'mainMenu.dart';
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
  final user = usuarioInfo(nome: "David", matricula: 261032,cpf: '70140422110',curso: 'Engenharia de Computacao',data_de_expiracao: '2023-01-01',url: 'https://dac.unicamp.br/pics/123456');

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
    _logarUsuario();
    Navigator.of(context).pushAndRemoveUntil(
      MaterialPageRoute<void>(
        builder: (context) {
          return mainMenu(inform: user,);
        },
      ),
      (route) => false,
    );
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
    }
    else {
      prefs.remove('email');
    }
  }

void _logarUsuario() async {
    // retorna um json
    // json[]
    // final response = await http.post(
    //     Uri.parse('localhost:8080/login'),
    //     headers: <String, String>{
    //       'Content-Type': 'application/json; charset=UTF-8',
    //     },
    //     body: jsonEncode({
    //       "ra": raController.text,
    //       "password": passwordController.text,
    //     })
    // );
    //
    // print(jsonDecode(response.body));
    //
    // if (response.statusCode == 201) {
    //   var jsonobject = jsonDecode(response.body);
    //   var key = jsonobject['key'];
    //   if (jsonobject['status'] == 'success') {
    //     return usuarioInfo.fromJson(jsonDecode(response.body));
    //   }
    //   else {
    //     throw Exception('Failed to login');
    //   }
    // } else {
    //   throw Exception('Failed to login.');
    // }

    // var headers = {
    //   'Content-Type': 'application/json'
    // };
    // var request = http.Request('POST', Uri.parse('localhost:8080/login'));
    // request.body = json.encode({
    //   "ra": 261032,
    //   "password": "password"
    // });
    // request.headers.addAll(headers);
    //
    // http.StreamedResponse response = await request.send();
    //
    // print(response.toString());
    //
    // if (response.statusCode == 200) {
    //   print(await response.stream.bytesToString());
    // }
    // else {
    //   print(response.reasonPhrase);
    // }

  var response = await http.get(Uri.parse('http://127.0.0.1:8080/'));
  print(response.toString());
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
