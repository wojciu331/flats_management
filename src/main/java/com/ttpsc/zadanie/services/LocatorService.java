package com.ttpsc.zadanie.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.ttpsc.zadanie.model.entities.Locator;
import com.ttpsc.zadanie.model.repositories.SharedLocatorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class LocatorService {
    @Autowired
    SharedLocatorsRepo locatorsRepo;

    public Optional<Locator> findOne(Long id) {
        return this.locatorsRepo.getRepo().findById(id);
    }

    /*public void deleteLocator(Long locatorId) {
        List<Locator> temp = new ArrayList<>();
        for (int i=1; i<locatorId; i++){
            temp.add(this.locatorsRepo.getRepo().findById((long) i).get());
        }
        int max = FlatsService.getNumberOfLocators();
        for(int j = (int)(locatorId+1); j<max; j++){
            temp.add(this.locatorsRepo.getRepo().findById((long) j).get());
        }
        FlatsService.setNumberOfLocators(max-1);
        for(int k=0; k<temp.size(); k++){
            this.locatorsRepo.getRepo().save(temp.get(k));
        }
    }*/

    public void create_invoice(Long id, String name, String surname, String email, Long rentCost) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("faktura_"+id.toString()+".pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        //w komentarzu - dla testów
        Date now = new Date();
        Chunk chunk = new Chunk(now.toString(), font);
        document.add(chunk);
        document.add(Chunk.NEWLINE);
        //document.add(new Paragraph("Sz. P. Robert Maklowicz", font));
        document.add(new Paragraph("Sz. P. "+name+surname, font));
        document.add(Chunk.NEWLINE);
        //document.add(new Paragraph("adres poczty elektronicznej: robcio_mak@gmail.com", font));
        document.add(new Paragraph("adres poczty elektronicznej: "+email, font));
        document.add(Chunk.NEWLINE);
        //document.add(new Paragraph("Do zaplaty: 1500 PLN", font));
        document.add(new Paragraph("Do zaplaty: "+rentCost.toString(), font));
        document.close();
    }
}
