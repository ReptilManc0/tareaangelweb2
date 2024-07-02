package Model;

import Datos.Conexion;
import Entity.ECards;
import Entity.ECliente;
import Entity.EResponse;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

     private static final String INSERT_SQL = "INSERT INTO cliente (descnombre,descapellido,descemail,numtelefono,mntsaldo) VALUES (?,?,?,?,?)";
    private static final String SELECT_SQL = "SELECT idcliente,descnombre,descapellido,descemail,numtelefono,mntsaldo FROM cliente WHERE flgestado = 1";
    private static final String UPDATE_SQL = "UPDATE cliente SET descnombre = ?, descapellido = ?, descemail = ?, numtelefono = ?, mntsaldo = ? WHERE idcliente = ?";
    private static final String DELETE_SQL = "UPDATE cliente SET flgestado = 0 WHERE idcliente = ?";

    public static EResponse insertCliente(ECliente objCliente) throws SQLException {
        EResponse<EResponse> response = new EResponse<>();
        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(INSERT_SQL);
            ps.setString(1, objCliente.getDescnombre());
            ps.setString(2, objCliente.getDescapellido());
            ps.setString(3, objCliente.getDescemail());
            ps.setString(4, objCliente.getNumtelefono());
            ps.setFloat(5, objCliente.getMntsaldo());
            int exec = ps.executeUpdate();
            if (exec > 0) {
                response.setSuccess(true);
                response.setStatus("200");
                response.setMessage("Se insertó correctamente el cliente");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setStatus("500");
            response.setMessage(e.getMessage());
        } finally {
            Conexion.closeConnection(cn);
        }
        return response;
    }

    public static List<ECliente> getCliente() throws SQLException {
        List<ECliente> lstCliente = new ArrayList<>();
        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(SELECT_SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ECliente objCliente = new ECliente();
                objCliente.setIdcliente(rs.getInt(1));
                objCliente.setDescnombre(rs.getString(2));
                objCliente.setDescapellido(rs.getString(3));
                objCliente.setDescemail(rs.getString(4));
                objCliente.setNumtelefono(rs.getString(5));
                objCliente.setMntsaldo(rs.getFloat(6));
                lstCliente.add(objCliente);
            }
        } catch (Exception e) {
        } finally {
            Conexion.closeConnection(cn);
        }
        return lstCliente;
    }

    public static EResponse updateCliente(ECliente objCliente) throws SQLException {
        EResponse<EResponse> response = new EResponse<>();
        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(UPDATE_SQL);
            ps.setString(1, objCliente.getDescnombre());
            ps.setString(2, objCliente.getDescapellido());
            ps.setString(3, objCliente.getDescemail());
            ps.setString(4, objCliente.getNumtelefono());
            ps.setFloat(5, objCliente.getMntsaldo());
            ps.setInt(6, objCliente.getIdcliente());
            int exec = ps.executeUpdate();
            if (exec > 0) {
                response.setSuccess(true);
                response.setStatus("200");
                response.setMessage("Se actualizó correctamente el cliente");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setStatus("500");
            response.setMessage(e.getMessage());
        } finally {
            Conexion.closeConnection(cn);
        }
        return response;
    }

    public static EResponse deleteCliente(int idCliente) throws SQLException {
        EResponse<EResponse> response = new EResponse<>();
        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(DELETE_SQL);
            ps.setInt(1, idCliente);
            int exec = ps.executeUpdate();
            if (exec > 0) {
                response.setSuccess(true);
                response.setStatus("200");
                response.setMessage("Cliente eliminado correctamente");
            } else {
                response.setSuccess(false);
                response.setStatus("404");
                response.setMessage("Cliente no encontrado");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setStatus("500");
            response.setMessage(e.getMessage());
        } finally {
            Conexion.closeConnection(cn);
        }
        return response;
    }
    
    public static ECards getCardsClient() throws SQLException {
        Connection cn = Conexion.getConnection();
        ECards card = new ECards();
        try {
            //Deben cambiar el procedimiento almacenado (recuerden que esta sin parametros)
            CallableStatement cs = cn.prepareCall("{CALL getCardsCliente()}");
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                card.setTotalClientes(rs.getInt("totalClientes"));
                card.setTotalSaldo(rs.getFloat("totalSaldo"));
                card.setTotalClienteTelf(rs.getInt("totalClienteTelf"));
            }
        } finally {
            Conexion.closeConnection(cn);
        }
        return card;
    }
}
