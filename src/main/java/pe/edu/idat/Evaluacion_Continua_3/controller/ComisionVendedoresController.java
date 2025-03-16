package pe.edu.idat.Evaluacion_Continua_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.Evaluacion_Continua_3.model.ComisionVendedoresModel;

@Controller
public class ComisionVendedoresController {
    @GetMapping("/comisionvendedores")
    public String formularioComisionVendedores(Model model) {
        model.addAttribute("comisionvendedoresmodel",
                new ComisionVendedoresModel());
        model.addAttribute("visualizaralerta",
                false);
        return "comisionvendedores";

    }

    @PostMapping("/calcularcomisionvendedores")
    public String calcularcomisionvendedores(@ModelAttribute("comisionvendedoresmodel") ComisionVendedoresModel comisionvendedores,
                                             Model model) {
        Double totalVentasMes = comisionvendedores.getTotalventasmes();
        Double valorComisionVendedores = null;
        String estiloDiagnostico = "alert-succes";

        if (totalVentasMes <= 1000) {
            valorComisionVendedores = totalVentasMes * 0.03;
        } else if (totalVentasMes > 1000 && totalVentasMes <= 5000) {
            valorComisionVendedores = totalVentasMes * 0.05;
        } else if (totalVentasMes > 5000 && totalVentasMes <= 10000) {
            valorComisionVendedores = totalVentasMes * 0.07;
        } else{
            valorComisionVendedores = totalVentasMes * 0.10;
        }

        model.addAttribute("resultado",
                "su comision del mes es de: $" + String.format("%.2f",valorComisionVendedores));
        model.addAttribute("visualizaralerta",
                true);
        model.addAttribute("estilodiagnostico",
                estiloDiagnostico);
        return "comisionvendedores";
    }
}