import 'package:flutter/material.dart';

class SplashBody extends StatelessWidget {
  const SplashBody({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Expanded(
          child: Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                  image: AssetImage("assets/splash.jpg"), fit: BoxFit.cover),
            ),
          ),
        ),
      ],
    );
  }
}