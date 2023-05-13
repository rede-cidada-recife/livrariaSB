import 'package:flutter/material.dart';
import 'dart:convert' as convert;
import 'package:http/http.dart' as http;

void main() {
  runApp(const LivrariaApp());
}

class LivrariaApp extends StatelessWidget {
  const LivrariaApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'App Livraria',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Livraria - Olar'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  
  void buscarLivros() async {

    final url = Uri.https('www.googleapis.com','/books/v1/volumes',{'q': '{http}'},);

    final resposta = await http.get(url);

    if (resposta.statusCode == 200) {

      final jsonResposta = convert.jsonDecode(resposta.body);
      print('O total de livros é : ' + jsonResposta['totalItems'].toString());
      
    }else{
      print('falha na requisição! Status code da requisição: ' + resposta.statusCode.toString());
    }


    /*
    Uri.http(String authority, [String unencodedPath, Map<String, dynamic>? queryParameters])
    Creates a new http URI from authority, path and query.

    Example:

    var uri = Uri.http('example.org', '/path', { 'q' : 'dart' });
    print(uri); // http://example.org/path?q=dart

    uri = Uri.http('user:password@localhost:8080', '');
    print(uri); // http://user:password@localhost:8080

    uri = Uri.http('example.org', 'a b');
  */

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Padding(
        padding: const EdgeInsets.all(18),
        child: ListView(
          children: [
            const TextField(),
            const SizedBox(height: 18),
            ElevatedButton.icon(
                onPressed: buscarLivros,
                icon: const Icon(Icons.search),
                label: const Text('Pesquisar')),
            const SizedBox(height: 18),
            const Text('Foram encontrados X livros: '),
          ],
        ),
      ),
    );
  }

}


