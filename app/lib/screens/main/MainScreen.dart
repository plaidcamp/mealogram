import 'package:flutter/material.dart';
import 'package:gourmetlog/common/auth/AuthManager.dart';
import 'package:provider/provider.dart';

class MainScreen extends StatefulWidget {
  @override
  _MainScreenState createState() => _MainScreenState();

}

class _MainScreenState extends State<MainScreen> {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<AuthManager>(
      create: (_) => AuthManager(),
      child: Scaffold(),
    );
  }
  
}
