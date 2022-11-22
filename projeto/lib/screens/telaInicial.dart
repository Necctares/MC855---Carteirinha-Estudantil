import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'usuarioInfo.dart';
import 'mainMenu.dart';

class telaInicial extends StatefulWidget {
  const telaInicial({Key? key}) : super(key: key);

  @override
  State<telaInicial> createState() => _telaInicialState();
}

class _telaInicialState extends State<telaInicial> {

  final emailController = TextEditingController();
  final passwordController = TextEditingController();
  bool rememberEmail = false;
  String email = '';
  String? password;
  final user = usuarioInfo(nome: "Pedro", matricula: 23456, saldo: 7.13);

  @override
  void initState() {
    super.initState();
    _getEmail();
  }

  @override
  void dispose() {
    emailController.dispose();
    passwordController.dispose();
    super.dispose();
  }

  void _logar() {
    _saveEmail();
    Navigator.of(context).pushAndRemoveUntil(
      MaterialPageRoute<void>(
        builder: (context) {
          return mainMenu(inform: user,);
        },
      ),
      (route) => false,
    );
  }

  Future _getEmail() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      rememberEmail = prefs.getBool('rememberEmail') ?? false;
      email = prefs.getString('email') ?? '';
      if (rememberEmail == true) {
        emailController.text = email;
      }
    });
  }

  Future _saveEmail() async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setBool('rememberEmail', rememberEmail);
    if (rememberEmail) {
      prefs.setString('email', emailController.text);
    }
    else {
      prefs.remove('email');
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
                    hintText: 'E-mail',
                  ),
                  controller: emailController,
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
                    value: rememberEmail,
                    onChanged: (bool? value) {
                      setState(() {
                        rememberEmail = value!;
                      });
                    },
                  ),
                  Text('Lembrar Email')
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
