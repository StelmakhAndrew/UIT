package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Document;
import project.com.Entity.DocumentPackage;
import project.com.Service.DocumentPackageService;
import project.com.Service.DocumentService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentPackageService documentPackageService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting(Model model) {

        Set<String> allInn = new HashSet<>();

        documentPackageService.findAll().forEach(documentPackage -> allInn.add(documentPackage.getInn()));

        model.addAttribute("allInn", allInn);
        return "greeting";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String greetings(Model model, @RequestParam(name = "inn") String inn) {
        //  SELECT * FROM public.documentpackage WHERE inn = 'inn'
        List<DocumentPackage> documentPackageList = documentPackageService.findAllByInn(inn);

        if (documentPackageList.size() == 0)
            return "redirect:/";

        model.addAttribute("inn", inn);
        model.addAttribute("package", documentPackageList);
        return "inn";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editData(@RequestParam(name = "inn") String inn,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "data") String data) {
        //SELECT * FROM public.documentpackage AS package WHERE package.inn = 'ds' LIMIT 1
        DocumentPackage documentPackage = documentPackageService.findFirstByInn(inn);

        DocumentPackage newDocumentPackage = documentPackage.getNewVersion(name, data);

        documentPackageService.createDocumentPackage(newDocumentPackage);

        for (Document aChild_component : documentPackage.getChild_component()) {
            Document newDocument = aChild_component.getNewVersion();
            newDocument.setParent_component(newDocumentPackage);
            documentService.createDocument(newDocument);
            newDocumentPackage.addChild_component(newDocument);
        }
        documentPackageService.createDocumentPackage(newDocumentPackage);
        return "redirect:/";
    }
}
