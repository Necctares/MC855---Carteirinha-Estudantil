class usuarioInfo {
  const usuarioInfo({
    required this.nome,
    required this.matricula,
    required this.cpf,
    required this.curso,
    required this.url,
    required this.data_de_expiracao,
  });

  final nome;
  final matricula;
  final cpf;
  final curso;
  final url;
  final data_de_expiracao;

  factory usuarioInfo.fromJson(Map<String, dynamic> json) {
    var student = json.values.toList()[2].values.toList();

    return usuarioInfo(
        matricula: student[0],
        nome: student[1],
        curso: student[2],
        url: student[3],
        data_de_expiracao: student[4],
        cpf: student[5]);
  }
}
