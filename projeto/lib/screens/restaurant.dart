class restaurant {
  const restaurant(
      {
        required this.ra,
        required this.credits,
        required this.is_post_paid,
        required this.already_ate
      });

  final ra;
  final credits;
  final is_post_paid;
  final already_ate;

  factory restaurant.fromJson(Map<String, dynamic> json) {
    var rest = json.values.toList()[2].values.toList();
    return restaurant(
      ra: rest[0],
      credits: rest[1],
      is_post_paid: rest[2],
      already_ate: rest[3]
    );
  }

}