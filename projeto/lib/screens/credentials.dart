class credentials {
  const credentials(
      {
        required this.response,
        required this.message,
        required this.accessToken,
      });

  final response;
  final message;
  final accessToken;

  factory credentials.fromJson(Map<String, dynamic> json) {
    return credentials(
        response: json['response'],
        message: json['message'],
        accessToken: json['loginvo']['accessToken'],
    );
  }

}