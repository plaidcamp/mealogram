import 'package:flutter/foundation.dart';

class SignInResponse {
  final int userId;

  SignInResponse ({
    @required this.userId,
  });

  factory SignInResponse.fromJson(Map<String, dynamic> json) {
    return SignInResponse(
      userId: json['userId'] as int,
    );
  }

}