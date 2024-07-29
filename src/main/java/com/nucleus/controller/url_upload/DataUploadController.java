package com.nucleus.controller.url_upload;


import com.nucleus.dto.link.LinkDTO;
import com.nucleus.service.url_upload.UrlUploadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/upload")
public class DataUploadController {
    @Autowired
    private UrlUploadService urlUploadService;

    @Autowired
    private Logger logger;

    @GetMapping
    public String showAddLinkForm(Model model, @RequestParam(value = "success", required = false) String success) {
        model.addAttribute("link", new LinkDTO());
        if (success != null && success.equals("true")) {
            model.addAttribute("uploadSuccess", true);
        }
        return "url_upload/UrlUploadForm";
    }

    // to handle form submission and add a new link
    @PostMapping
    public String uploadUrlData(@ModelAttribute("link") LinkDTO linkDTO,
                                @RequestParam("keyword") String keyword,
                                Model model) {
        //creating id
        String uniqueId = "URL_" + urlUploadService.getNextSequence();
        linkDTO.setId(uniqueId);

        logger.info("(Controller) Form Data : " + linkDTO);

        boolean status = urlUploadService.savaUrlData(linkDTO ,keyword);

        if(!status){
            logger.info("Error Occurred during URL Data Upload!!");
            return "errorPage";
        }else{
            logger.info("URL Data Uploaded Successfully!!");
            model.addAttribute("successMessage", "URL Data Uploaded Successfully!!");
            model.addAttribute("link", new LinkDTO()); // Reset the form
            return "redirect:/upload?success=true";
        }
    }
}
