package com.abit8.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        try (var serverSocket = new ServerSocket(8081);
             var socket = serverSocket.accept();//открытие сервера, разрешение на сервер
             var inputStream = new DataInputStream(socket.getInputStream());
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var scanner = new Scanner(System.in)) {
            var request = inputStream.readUTF();
            while (!request.equals("exit")) {
                System.out.println("Client: " + request);
                outputStream.writeUTF(scanner.nextLine());
                request = inputStream.readUTF();
            }

            /*
            возвращаемые сервером ответы
            100-199 = информационные ответы
            200-299 = успешное выполнение запроса
            300-399 = редиректы(перенаправил на другой адрес
            400-499 = клиентские ошибки
            500-599 = ошибка на стороне сервера
             */
        }
    }
}
