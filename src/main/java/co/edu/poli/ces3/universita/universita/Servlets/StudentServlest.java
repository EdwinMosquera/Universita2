package co.edu.poli.ces3.universita.universita.Servlets;

import co.edu.poli.ces3.universita.universita.Mode.Students;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "studentServlet", value = "/student")
public class StudentServlest extends MyServlet {
    private String message;

    private GsonBuilder gsonBuilder;
    private Gson gson;
    private ArrayList<Students> student = new ArrayList<>();

    public void init() {

        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        Students student1 = new Students();
        student1.setId(12);
        student1.setNombre("Edwin");
        student1.setDocumento("1234567");

        Students student2 = new Students();
        student2.setId(15);
        student2.setNombre("Nicolas");
        student2.setDocumento("9876543");

        student.add(student1);
        student.add(student2);

        for (int i=0; i<student.size(); i++){

            student.toString();
        }

        message = "student";
    }


    public StudentServlest(){
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("studentId");
        if (id == null){
            out.println(gson.toJson(student));
        }else {
            Students studentsBuscar = null;
            for (Students s: student){
                if (s.getId()== Integer.parseInt(id)){
                    studentsBuscar = s;
                    break;
                }
            }
            out.println(gson.toJson(studentsBuscar));
        }
        // Hello


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");

        JsonObject body = this.getParamsFromPost(req);

        Students students = new Students(
                body.get("id").getAsInt(),
                body.get("nombre").getAsString(),
                body.get("documento").getAsString()
        );

        this.student.add(students);

        out.print(gson.toJson(students));
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(req);

        if (body != null){
            for (int i =0; i<student.size(); i++){
                if (student.get(i).getId()== body.get("id").getAsInt()){
                    this.student.get(i).setNombre(body.get("nombre").getAsString());
                    this.student.get(i).setDocumento(body.get("documento").getAsString());
                    out.println(gson.toJson(student));
                    break;
                }
            }
            out.println(gson.toJson(null));
        }else {
            out.println(gson.toJson(student));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(req);

        if (body != null){
            for (int i =0; i<student.size(); i++){
                if (student.get(i).getId()== body.get("id").getAsInt()){
                    this.student.remove(i);
                    out.println(gson.toJson(student));
                    break;
                }
            }
            out.println(gson.toJson(null));
        }else {
            out.println(gson.toJson(student));
        }
    }

    public void destroy() {
    }
}