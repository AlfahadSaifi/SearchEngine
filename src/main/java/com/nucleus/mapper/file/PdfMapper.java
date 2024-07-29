package com.nucleus.mapper.file;

import com.nucleus.dto.file.PdfDTO;
import com.nucleus.model.engine.Pdf;

public class PdfMapper {
    private PdfMapper(){}

    public static PdfDTO toDTO(Pdf pdf) {
        if (pdf == null) {
            return null;
        }

        return new PdfDTO(
                pdf.getId(),
                pdf.getFileTitle(),
                pdf.getFileData(),
                pdf.getFileKeywords(),
                pdf.getFileDescription()
        );
    }

    public static Pdf toEntity(PdfDTO pdfDTO) {
        if (pdfDTO == null) {
            return null;
        }
        Pdf pdf = new Pdf();
        pdf.setId(pdfDTO.getId());
        pdf.setFileTitle(pdfDTO.getFileTitle());
        pdf.setFileData(pdfDTO.getFileData());
        pdf.setFileKeywords(pdfDTO.getFileKeywords());
        pdf.setFileDescription(pdfDTO.getFileDescription());
        return pdf;
    }
}

