package net.coding.samples;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        Map<String, String[]> params = request.getParameterMap();
        if (!params.isEmpty()) {
            out.println("Request Parameters:");
            for (String key : params.keySet()) {
                String[] values = params.get(key);
                out.println(key + ": " + Arrays.toString(decode(values)));
            }
            out.println();
        }
        out.println("System Environment:\n");
        for (Map.Entry<String, String> envvar : System.getenv().entrySet()) {
            out.println(envvar.getKey() + ": " + envvar.getValue());
        }
        out.close();
    }

    private String[] decode(String[] values) {
        if (values == null ) {
            return null;
        }
        String[] new_values = new String[values.length];
        for(int i = 0; i < values.length; i++) {
            String value = values[i];
            try {
                new_values[i] = new String(value.getBytes("8859_1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                new_values[i] = value;
            }
        }
        return new_values;
    }
}
