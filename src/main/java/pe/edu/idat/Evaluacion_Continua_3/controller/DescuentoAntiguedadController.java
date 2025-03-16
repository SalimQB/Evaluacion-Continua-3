package pe.edu.idat.Evaluacion_Continua_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.Evaluacion_Continua_3.model.DescuentoAntiguedadModel;

@Controller
public class DescuentoAntiguedadController {
    @GetMapping("/descuentoaplicable")
    public String formularioDescuentoAntiguedad(Model model) {
        model.addAttribute("descuentoantiguedadmodel",
                new DescuentoAntiguedadModel());
        model.addAttribute("visualizaralerta",
                false);
        return "descuentoaplicable";

    }

    @PostMapping("/calculardescuentoaplicable")
    public String calculardescuentoaplicable(@ModelAttribute("descuentoantiguedadmodel") DescuentoAntiguedadModel calculardescuentoaplicable,
                                             Model model) {
        DescuentoAntiguedadModel descuentoaplicable = null;
        Double descuentocli = descuentoaplicable.getDescuento();
        Double desctotal = null;
        String estiloDiagnostico = "alert-succes";

        if (descuentocli <= 1) {
            desctotal = descuentocli * 0.02;
        } else if (descuentocli > 1 && descuentocli <= 3) {
            desctotal = descuentocli * 0.05;
        } else if (descuentocli > 3 && descuentocli <= 5) {
            desctotal = descuentocli * 0.08;
        } else {
            desctotal = descuentocli * 0.12;
        }

        model.addAttribute("resultado",
                "su descuento por antiguedad es: $" + String.format("%.2f",desctotal));
        model.addAttribute("visualizaralerta",
                true);
        model.addAttribute("estilodiagnostico",
                estiloDiagnostico);
        return "descuentoaplicable";
    }
}