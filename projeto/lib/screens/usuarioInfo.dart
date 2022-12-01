class usuarioInfo {
  const usuarioInfo(
      {
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
    return usuarioInfo(
        nome: json['student']['name'],
        matricula: json['student']['ra'],
        cpf: json['student']['cpf'],
        curso: json['student']['course'],
        url: json['student']['url'],
        data_de_expiracao: json['student']['expirationDate']
    );
  }

}
