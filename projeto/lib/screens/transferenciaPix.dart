class transferenciaPix {
  const transferenciaPix({
    required this.status,
    required this.txid,
    required this.valor,
    required this.qrcode,
  });

  final status;
  final txid;
  final valor;
  final qrcode;

  factory transferenciaPix.fromJson(Map<String, dynamic> json) {
    var resposta_bb = json.values.toList()[2].values.toList();
    var valor = json.values.toList()[2].values.toList()[7].values.toList();
    return transferenciaPix(
      status: resposta_bb[0],
      txid: resposta_bb[4],
      valor: valor[0],
      qrcode: resposta_bb[3],
    );
  }
}
