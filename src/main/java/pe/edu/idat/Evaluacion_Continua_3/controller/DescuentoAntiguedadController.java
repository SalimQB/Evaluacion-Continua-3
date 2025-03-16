package pe.edu.idat.Evaluacion_Continua_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.Evaluacion_Continua_3.model.DescuentoAntiguedadModel;

@Controller
public class DescuentoAntiguedadController {
    @GetMapping("/descuentoantiguedad")
    public String formulariodescuento(Model model) {
        model.addAttribute("descuentomodel",
                new DescuentoAntiguedadModel());
        model.addAttribute("visualizaralerta" , false);
        return "descuentoantiguedad";
    }

    @PostMapping("/calculardescuento")
    public String calculardescuento(@ModelAttribute("descuentomodel") DescuentoAntiguedadModel calculardescuento, Model model){

        double annos1 = calculardescuento.getAnnos();
        double monto1 = calculardescuento.getMonto();
        double total = 0;
        String diagnostico = "";
        String estiloDiagnostico = "alert-danger";

        if (annos1 < 1) {
            total = monto1 - (monto1 * .02);
            estiloDiagnostico = "alert-danger";
        } else if (annos1 < 3) {
            total = monto1 - (monto1 * .05);
            estiloDiagnostico = "alert-dark";
        } else if (annos1 < 5 ) {
            total = monto1 - (monto1 * .08);
            estiloDiagnostico = "alert-warning";
        } else {
            total = monto1 - (monto1 * .12);
            estiloDiagnostico = "alert-primary";
        }

        model.addAttribute("resultado", "su descuento es  " +  total );

        model.addAttribute("visualizaralerta" , true);

        model.addAttribute("estiloDiagnostico", estiloDiagnostico);
        return "descuentoantiguedad";
    }


}