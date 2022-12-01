import 'package:flutter/material.dart';
import 'menuLateral.dart';
import 'notification_service.dart';
import 'package:provider/provider.dart';

class confignot extends StatefulWidget {
  const confignot({super.key, required this.inform});

  final inform;

  @override
  State<confignot> createState() => _confignotState();
}

class _confignotState extends State<confignot> {
  bool valor = false;
  var _opcoes = ['Diariamente', 'Semanalmente', 'Mensalmente'];
  String dropDownStringItem = "Diariamente";
  confignot get widget => super.widget;

  showNotification() {
    setState(() {
      valor = !valor;
      if (valor) {
        Provider.of<NotificationService>(context, listen: false)
            .showNotification(
          CustomNotification(
              id: 1, title: 'Carteirinha Unicamp', body: 'Saldo: R\$45.00'),
        );
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: menuLateral(
        inform: widget.inform,
      ),
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
              Text(
                'Configuração de Notificações',
                textAlign: TextAlign.center,
                style: TextStyle(
                    height: 5, fontSize: 25, fontWeight: FontWeight.bold),
              ),
              SizedBox(
                height: 50,
              ),
              Container(
                width: 550,
                alignment: Alignment.center,
                child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: <Widget>[
                      Text(
                        'Habilitar Notificações',
                        // textAlign: TextAlign.center,
                        style: TextStyle(fontSize: 18),
                      ),
                      Switch(
                        value: valor,
                        onChanged: (bool value) {
                          showNotification();
                        },
                      ),
                    ]),
              ),
              SizedBox(
                height: 25,
              ),
              Container(
                width: 308,
                alignment: Alignment.center,
                child: Row(children: <Widget>[
                  Text(
                    'Frequência',
                    // textAlign: TextAlign.center,
                    style: TextStyle(fontSize: 18),
                  ),
                  Container(
                    width: 60,
                    alignment: Alignment.center,
                  ),
                  DropdownButton<String>(
                    borderRadius: BorderRadius.all(Radius.circular(25)),
                    value: dropDownStringItem,
                    items: _opcoes.map((value) {
                      return DropdownMenuItem<String>(
                        value: value,
                        child: Text(value),
                      );
                    }).toList(),
                    onChanged: (String? newValue) {
                      setState(() {
                        dropDownStringItem = newValue!;
                      });
                    },
                  ),
                ]),
              ),
              SizedBox(
                height: 25,
              ),
              Container(
                width: 304,
                alignment: Alignment.center,
                child: Row(children: <Widget>[
                  Text(
                    'Saldo mínimo',
                    // textAlign: TextAlign.center,
                    style: TextStyle(fontSize: 18),
                  ),
                ]),
              ),
            ]),
      ),
    );
  }
}
