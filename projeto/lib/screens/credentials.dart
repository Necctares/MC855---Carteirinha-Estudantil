class credentials {
  const credentials({
    required this.response,
    required this.message,
    required this.accessToken,
  });

  final response;
  final message;
  final accessToken;

  factory credentials.fromJson(Map<String, dynamic> json) {
    var acessToken;
    if (json.containsKey('loginvo') &&
        json['loginvo'].keys.toList()[1] == 'accessToken') {
      acessToken = json['loginvo'].values.toList()[1];
    } else {
      acessToken = "";
    }
    return credentials(
      response: json['response'],
      message: json['message'],
      accessToken: acessToken,
    );
  }
}
