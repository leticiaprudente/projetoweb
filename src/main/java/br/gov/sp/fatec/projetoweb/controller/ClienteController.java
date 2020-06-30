package br.gov.sp.fatec.projetoweb.controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.gov.sp.fatec.lab4.dao.*;
import br.gov.sp.fatec.lab4.entities.*;

public class ClienteController extends HttpServlet {
        private static final long serialVersionUID = 1L;
        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        // Recupera o parâmetro id (de trabalho?id=<valor>)
        Long id = Long.valueOf(req.getParameter("id"));
        // Busca cliente com o id
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.buscarCliente(id);
        // Usamos o Jackson para transformar o objeto em um JSON (String)
        ObjectMapper mapper = new ObjectMapper();
        String clienteJson = mapper.writeValueAsString(cliente);
        // Formatamos a resposta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);
        PrintWriter out = resp.getWriter();
        out.print(clienteJson);
        out.flush();
    }
}