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
    return transferenciaPix(
        status: json['objectnode']['status'],
        txid: json['objectnode']['txid'],
        valor: json['objectnode']['valor']['original'],
        qrcode: json['objectnode']['textoImagemQRcode'],
    );
  }

}