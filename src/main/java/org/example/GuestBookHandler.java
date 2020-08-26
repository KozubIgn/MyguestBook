package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.model.Inscription;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class GuestBookHandler implements HttpHandler {
    ArrayList<Inscription> inscriptionList;
    Helpers helpers;

    public GuestBookHandler() {
        inscriptionList = new ArrayList<>();
        this.helpers = new Helpers();
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            String method = exchange.getRequestMethod();

            if (method.equals("GET")) {
                getTemplate(exchange);

            }
            if (method.equals("POST")) {
                InputStreamReader isr =
                        new InputStreamReader
                                (exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String formData = br.readLine();

                System.out.println(formData);
                Map<String, String> inputs = Helpers.parseFormData(formData);

                inscriptionList.add(new Inscription(inputs.get("name"), inputs.get("content"), LocalDateTime.now()));
               getTemplate(exchange);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTemplate(HttpExchange exchange) throws IOException {
        String response;
        JtwigTemplate template =
                JtwigTemplate.classpathTemplate
                        ("templates/index.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("inscriptionList",
                inscriptionList);

        response = template.render(model);
        helpers.sendResponse(exchange, response);
    }
}