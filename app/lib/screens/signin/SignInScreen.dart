import 'package:flutter/material.dart';
import 'components/SignInBody.dart';
import 'package:gourmetlog/common/components/color.dart';

class SignInScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "SignIn page",
      theme: ThemeData(
        brightness: Brightness.dark,
        primaryColor: kPrimaryColor,
        scaffoldBackgroundColor: kBackgroudColor,
        textTheme: TextTheme(
          display1: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
          button: TextStyle(color: kPrimaryColor),
          headline: TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 25)
        ),
      ),
      home: SignInBody(),
    );
  }
}

