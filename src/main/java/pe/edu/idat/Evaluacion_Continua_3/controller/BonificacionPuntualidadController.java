package pe.edu.idat.Evaluacion_Continua_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.Evaluacion_Continua_3.model.BonificacionPuntualidadModel;

@Controller
public class BonificacionPuntualidadController {
    @GetMapping("/bonificacionpuntualidad")
    public String formularioBonificacionPuntualidad(Model model) {
        model.addAttribute("bonificacionpuntualidadmodel",
                new BonificacionPuntualidadModel());
        model.addAttribute("visualizaralerta",
                false);
        return "bonificacionpuntualidad";
    }
    @PostMapping("/calcularbonificacionpuntualidad")
    public String calcularbonificacionpuntualidad(@ModelAttribute("bonificacionpuntualidadmodel") BonificacionPuntualidadModel bonificacionpuntualidad,
                                             Model model) {
        Double montoPagar = bonificacionpuntualidad.getMontopagar();
        Integer tiempoTranscurrido = bonificacionpuntualidad.getTiempotranscurrido();
        Double bonoDescuento = 0.0;
        Double totalPagar = 0.0;
        String descuento;
        String estiloDiagnostico = "alert-succes";

        if (tiempoTranscurrido <= 7) {
            descuento = "Aplica descuento del 10% por puntualidad";
            bonoDescuento = montoPagar * 0.10;
            totalPagar = montoPagar - bonoDescuento;

        } else if (tiempoTranscurrido > 7 && tiempoTranscurrido <= 15) {
            descuento = "Aplica descuento del 5% por puntualidad";
            bonoDescuento = montoPagar * 0.05;
            totalPagar = montoPagar - bonoDescuento;
        } else {
            descuento = "No aplica a descuento";

            totalPagar = montoPagar;
        }

        model.addAttribute("resultado",
                "El total que debera pagar usted es de: s/" + String.format("%.2f",totalPagar) + " ,ya que usted: " + descuento);
        model.addAttribute("visualizaralerta",
                true);
        model.addAttribute("estilodiagnostico",
                estiloDiagnostico);
        return "bonificacionpuntualidad";
    }

}
