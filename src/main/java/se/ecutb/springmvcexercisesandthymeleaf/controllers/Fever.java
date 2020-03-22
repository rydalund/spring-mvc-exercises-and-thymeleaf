package se.ecutb.springmvcexercisesandthymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Fever {

    String diagnosis = null;
    String treatmentInstruction = null;

    @GetMapping("/temperature")
    public String bodyTemperature(Model model){
        return "temperature";
    }

    @PostMapping("/temperature")
    public String bodyTemperature(@RequestParam(name = "temp", defaultValue ="-1") double temp){
        if (temp > -1){
            if (temp < 25.0){
                diagnosis = "Error/Death";
                treatmentInstruction = "Error! - Body temperature should be in the range between 25–46.5 °C. Death usually occurs between 24–25 °C due to irregular heart beat or respiratory arrest; however, some patients have been known to survive with body temperatures as low as 14.2 °C.";
            }
            if (temp >= 25.0 && temp <= 32.0){
                diagnosis = "Hypothermia - Emergency!";
                treatmentInstruction = "Call 911 or your local emergency number if you suspect someone has hypothermia! While you wait for emergency help to arrive, gently move the person inside if possible. Jarring movements can trigger dangerous irregular heartbeats.";
            }
            else if(temp > 32.0 && temp < 35.0){
                diagnosis = "Hypothermia";
                treatmentInstruction = " Carefully remove his or her wet clothing, replacing it with warm, dry coats or blankets.";
            }else if(temp >= 35.0 && temp <= 37.5){
                diagnosis = "Normal";
                treatmentInstruction = "Your temperature is normal, have a good day!";
            }else if(temp > 37.5 && temp <= 46.5){
                diagnosis = "Fever";
                treatmentInstruction  = "Stay in bed and rest. Keep hydrated. Drinking water, iced tea, or very diluted juice to replenish fluids lost through sweating. But if keeping liquids down is difficult, suck on ice chips. Take over-the-counter medications like acetaminophen and ibuprofen to reduce fever. Note the proper dosage, and don’t them use alongside other fever-reducing medications. You shouldn’t give aspirin to your baby or child without consulting your doctor. Infants under 6 months of age shouldn’t be given ibuprofen. Stay cool. Remove extra layers of clothing and blankets, unless you have the chills. Take tepid baths or using cold compresses to make you more comfortable. Cold baths, ice cube baths, or alcohol baths or rubs can be dangerous and should be avoided. But no matter what the number on the thermometer reads, if you have any concerns consult your doctor.";
            }else if(temp > 46.5){
                diagnosis = "Error/Death";
                treatmentInstruction = "Error! - Body temperature should be in the range between 25–46.5 °C. Death almost certainly will occur at 44 °C or more; however, people have been known to survive up to 46.5 °C.";
            }
            return "redirect:/temperature/result";
        }
        return null;
    }

    @GetMapping("/temperature/result")
    public String treatment (Model model){
        model.addAttribute("diagnosis", diagnosis);
        model.addAttribute("treatment", treatmentInstruction);
        return "result";
    }
}
