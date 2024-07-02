package Controller;

import Entity.ECards;
import Entity.ECliente;
import Entity.EResponse;
import Model.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.JSONObject;

@WebServlet("/ServletCliente")
public class ClienteController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ECliente cliente = new ECliente();
            String type = request.getParameter("type");

            if ("add".equals(type)) {
                cliente.setDescnombre(request.getParameter("descnombre"));
                cliente.setDescapellido(request.getParameter("descapellido"));
                cliente.setDescemail(request.getParameter("descemail"));
                cliente.setNumtelefono(request.getParameter("numtelefono"));
                cliente.setMntsaldo(Float.parseFloat(request.getParameter("mntsaldo")));

                EResponse resultado = Cliente.insertCliente(cliente);
                JSONObject json = new JSONObject();
                json.put("success", resultado.isSuccess());
                json.put("message", resultado.getMessage());

                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(json);
                out.close();
            } else if ("update".equals(type)) {
                cliente.setIdcliente(Integer.parseInt(request.getParameter("idcliente")));
                cliente.setDescnombre(request.getParameter("descNombre"));
                cliente.setDescapellido(request.getParameter("descApellido"));
                cliente.setDescemail(request.getParameter("descEmail"));
                cliente.setNumtelefono(request.getParameter("numTelefono"));
                cliente.setMntsaldo(Float.parseFloat(request.getParameter("mntsaldo")));
                EResponse resultado = Cliente.updateCliente(cliente);
                JSONObject json = new JSONObject();
                json.put("success", resultado.isSuccess());
                json.put("message", resultado.getMessage());

                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(json);
                out.close();
            } else if ("delete".equals(type)) {
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                EResponse resultado = Cliente.deleteCliente(idCliente);
                JSONObject json = new JSONObject();
                json.put("success", resultado.isSuccess());
                json.put("message", resultado.getMessage());

                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(json);
                out.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String type = request.getParameter("type");

            List<ECliente> respuesta = (List<ECliente>) mantenimientoCliente(type, null);
            JSONObject json = new JSONObject();
            ECards card = Cliente.getCardsClient();
            json.put("body", respuesta);
            json.put("totalClientes", card.getTotalClientes());
            json.put("totalSaldo", card.getTotalSaldo());
            json.put("totalClienteTelf", card.getTotalClienteTelf());

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Object mantenimientoCliente(String type, ECliente cliente) throws SQLException {
        EResponse<EResponse> response = new EResponse<>();
        List<ECliente> lstCliente = new ArrayList<>();
        switch (type) {
            case "1":
                lstCliente = Cliente.getCliente();
                break;
        }
        if ("1".equals(type)) {
            return lstCliente;
        }
        return true;
    }
}
