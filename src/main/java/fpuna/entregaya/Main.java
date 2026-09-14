package fpuna.entregaya;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
    public static void main(String[] args) throws Exception {
        String host = args.length > 0 ? args[0] : "127.0.0.1";
        int puerto = args.length > 1 ? Integer.parseInt(args[1]) : 5001;
        String sucursalId = args.length > 2 ? args[2] : "SUC-01";
        String sku = args.length > 3 ? args[3] : "ARROZ-1K";
        String requestId = UUID.randomUUID().toString();

        JSONObject solicitud = new JSONObject();
        solicitud.put("tipo", "GET_CATALOGO");
        solicitud.put("requestId", requestId);
        solicitud.put("sucursalId", sucursalId);
        solicitud.put("sku", sku);

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, puerto), 3000);
            socket.setSoTimeout(3000);

            BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.UTF_8));
            BufferedReader entrada = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), StandardCharsets.UTF_8));

            salida.write(solicitud.toJSONString());
            salida.write("\n");
            salida.flush();

            String linea = entrada.readLine();
            if (linea == null) {
                throw new IllegalStateException("SuperMax cerro la conexion sin responder");
            }

            JSONObject respuesta = (JSONObject) new JSONParser().parse(linea);
            if (!Objects.equals(requestId, respuesta.get("requestId"))) {
                throw new IllegalStateException("El requestId de la respuesta no coincide");
            }
            if (!Boolean.TRUE.equals(respuesta.get("ok"))) {
                throw new IllegalStateException("SuperMax rechazo la consulta: " + linea);
            }

            System.out.println(respuesta.toJSONString());
        }
    }
}
