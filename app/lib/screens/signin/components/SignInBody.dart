import 'package:flutter/material.dart';
import 'package:gourmetlog/common/components/color.dart';
import 'package:gourmetlog/data/request/signin/SignInRequest.dart';
import 'package:gourmetlog/data/response/signin/SignInResponse.dart';
import 'dart:async';
import 'package:gourmetlog/screens/signup/SignUpScreen.dart';

class SignInBody extends StatelessWidget {
  final SignInRequest signInRequest = SignInRequest();
  SignInBody({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Column(
      children: <Widget>[
        Expanded(
          flex: 4,
          child: Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                  image: AssetImage("assets/test2.jpg"), fit: BoxFit.cover),
            ),
          ),
        ),
        Expanded(
          flex: 3,
          child: Column(
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.only(top: 8),
                child: RichText(
                    text: TextSpan(
                  children: [
                    TextSpan(
                      text: "Mealogram",
                      style: Theme.of(context).textTheme.display1.copyWith(
                            fontWeight: FontWeight.bold,
                          ),
                    ),
                  ],
                )),
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 16),
                child: Row(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    Padding(
                      padding: const EdgeInsets.only(right: 16),
                      child: Icon(
                        Icons.alternate_email,
                        color: kPrimaryColor,
                      ),
                    ),
                    Expanded(
                      child: TextField(
                          decoration: InputDecoration(hintText: "이메일 주소")),
                    ),
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 16),
                child: Row(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    Padding(
                      padding: const EdgeInsets.only(right: 16),
                      child: Icon(
                        Icons.lock,
                        color: kPrimaryColor,
                      ),
                    ),
                    Expanded(
                      child: TextField(
                          decoration: InputDecoration(hintText: "비밀번호")),
                    ),
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.symmetric(vertical: 16),
                child: GestureDetector(
                  onTap:() {
                    Navigator.push(context, MaterialPageRoute(
                      builder:(context) {
                        return Scaffold(
                          body: FutureBuilder(
                            future: signInRequest.getSignInInfo(),
                            builder: (BuildContext context, AsyncSnapshot<List<SignInResponse>> snapshot) {
                              if(snapshot.hasData) {
                                List<SignInResponse> responses = snapshot.data;
                                return ListView(
                                  children: responses.map((SignInResponse response) =>
                                      ListTile( title: Text(response.userId.toString()) )
                                  ).toList(),
                                );
                              }
                              return Center(child: CircularProgressIndicator());
                            },
                          )
                        );
                      },
                    ));
                  },
                  child: Container(
                    width: 350,
                    height: 50,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(30),
                      color: kPrimaryColor,
                    ),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text(
                          "Sign In",
                          style: Theme.of(context).textTheme.headline,
                        ),
                      ],
                    ),
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.symmetric(vertical: 8),
                child: Container(
                  width: 350,
                  height: 50,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(30),
                    color: kPrimaryColor,
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        "Sign Up",
                        style: Theme.of(context).textTheme.headline,
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ],
    ));
  }
}
