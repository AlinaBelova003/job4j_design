package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Создание сервера и клиента.
 * Сокет - комбинация IP И номера порта; розетка подключающая два устройства(терминал, сервер к компьютору)
 * ServerSocket нужен только на этапе создания клиетского сокета. Выбираем порт, к которому будем подключаться и ждем клиента
 * Как только клиент успешно подключился(accept), возвращяем объект сокета
 * Открываем поток с которого будем отправлять инфор. и поток куда будем записывать в HTTP формате(путь, версия, состояние)
 * Если мы запускаем строчку Bye, то выходим из сервера(разрываем соединение)
 * Читаем поток
 * flush() - дает горантию, что вся информация записана
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
       try (ServerSocket server = new ServerSocket(9000)) {
           while (!server.isClosed()) {
               Socket socket = server.accept();
               try (OutputStream out = socket.getOutputStream()) {
                   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                   out.write("HTTP/1.1 200 OK\\r\\n\\r\\n".getBytes());
                   String line = in.readLine();
                   if (line.contains("/?msg=Exit")) {
                       out.write("Завершить хост".getBytes());
                       server.close();
                   } else if (line.contains("/?msg=Hello")) {
                       System.out.println("Hello");
                       out.write("Hello".getBytes());
                   } else if (line.contains("/?msg=Any")) {
                       System.out.println("What?");
                       out.write("What".getBytes());
                   }
                   for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                       System.out.println(str);
                   }
                   out.flush();
               }
           }


       }

    }
}
