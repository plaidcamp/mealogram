import 'package:flutter/material.dart';
import '../signin/SignInScreen.dart';
import 'components/SplashBody.dart';

class SplashScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    waitSplash(context);
    return Scaffold(
        body:SplashBody(),
    );
  }

  Future<void> waitSplash(BuildContext context) async {
    await Future.delayed(const Duration(seconds: 2), (){
      Navigator.push(context, MaterialPageRoute(
        builder: (context) {
          return SignInScreen();
        },
      ));
    });
  }
}