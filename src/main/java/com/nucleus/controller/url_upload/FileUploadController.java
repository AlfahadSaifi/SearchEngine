package com.nucleus.controller.url_upload;

import com.nucleus.dto.file.PdfDTO;
import com.nucleus.service.url_upload.UrlUploadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/pdf")
public class FileUploadController {
    @Autowired
    private UrlUploadService urlUploadService;

    @Autowired
    private Logger logger;

    @GetMapping("/upload")
    public String showUploadForm(Model model,
                                 @RequestParam(value = "success", required = false) String success) {
        if (success != null && success.equals("true")) {
            model.addAttribute("uploadSuccess", true);
        }
        return "url_upload/uploadPDF";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("fileTitle") String fileTitle,
                             @RequestParam("fileDescription") String fileDescription,
                             @RequestParam("keyword") String fileKeywords,
                             @RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:upload";
        }

        try {
            String uniqueId = "FILE_"+urlUploadService.getNextSequence();
            PdfDTO pdf = new PdfDTO();
            pdf.setId(uniqueId);
            pdf.setFileTitle(fileTitle);
            pdf.setFileData(file.getBytes());
            pdf.setFileDescription(fileDescription);


            boolean status = urlUploadService.savaFileData(pdf, fileKeywords);

            if(!status){
                logger.info("Error Occurred during File Data Upload!!");
                return "errorPage";
            }else{
                logger.info("File Data Uploaded Successfully!!");
                model.addAttribute("successMessage", "File Data Uploaded Successfully!!");
                model.addAttribute("link", new PdfDTO()); // Reset the form
                return "redirect:/pdf/upload?success=true";
            }

        } catch (IOException e) {
            logger.error(e);
            return "errorPage";
        }
    }
}
