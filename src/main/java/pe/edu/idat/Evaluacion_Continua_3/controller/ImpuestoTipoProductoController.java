package pe.edu.idat.Evaluacion_Continua_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.Evaluacion_Continua_3.model.ImpuestoTipoProductoModel;


@Controller
public class ImpuestoTipoProductoController {
    @GetMapping("/impuestotipoproducto")
    public String formularioimpuesto(Model model) {
        model.addAttribute("impuestomodel",
                new ImpuestoTipoProductoModel());
        model.addAttribute("visualizaralerta" , false);
        return "impuestotipoproducto";
    }

    @PostMapping("/calcularimpuesto")
    public String calcularimpuesto(@ModelAttribute("impuestomodel") ImpuestoTipoProductoModel calcularimpuesto, Model model){

        String prod = calcularimpuesto.getProducto();
        double prec = calcularimpuesto.getPrecio();
        double total = 0;
        String diagnostico = "";
        String estiloDiagnostico = "alert-danger";

        if (prod.equalsIgnoreCase("Electrónica")) {
            total = prec - (prec * 0.15);
        } else if (prod.equalsIgnoreCase("Alimentos")) {
            total = prec - (prec * 0.05);
        } else if (prod.equalsIgnoreCase("Ropa")) {
            total = prec - (prec * 0.08);
        } else if (prod.equalsIgnoreCase("Muebles")) {
            total = prec - (prec * 0.10);
        } else {
            diagnostico = "Categoría no válida.";
            estiloDiagnostico = "alert-danger";
            model.addAttribute("visualizaralerta", true);
            model.addAttribute("estiloDiagnostico", estiloDiagnostico);
            model.addAttribute("resultado", diagnostico);
        }

        model.addAttribute("resultado", "su descuento es  " +  total );

        model.addAttribute("visualizaralerta" , true);

        model.addAttribute("estiloDiagnostico", estiloDiagnostico);
        return "impuestotipoproducto";
    }


}