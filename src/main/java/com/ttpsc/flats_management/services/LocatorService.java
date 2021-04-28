package com.ttpsc.flats_management.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.ttpsc.flats_management.controllers.LocatorsController;
import com.ttpsc.flats_management.model.entities.Flat;
import com.ttpsc.flats_management.model.entities.Locator;
import com.ttpsc.flats_management.model.repositories.FlatsRepo;
import com.ttpsc.flats_management.model.repositories.LocatorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class LocatorService {
    @Autowired
    private LocatorsRepo locatorsRepo;
    @Autowired
    private FlatsRepo flatsRepo;
    @Autowired
    private ConversionsService conversionsService;

    public Locator AddLocatorToFlat(LocatorsController.NewLocatorDto locator) {
        Locator locatorOne = conversionsService.convert(locator);
        Flat flat = this.flatsRepo.findById(locator.getFlatId()).orElse(null);
        this.flatsRepo.findById(locator.getFlatId()).ifPresent(locatorOne::setFlat);
        List<Locator> locatorList = flat.getLocators();
        locatorList.add(locatorOne);
        flat.setLocators(locatorList);
        flat.set_vacant(false);
        this.locatorsRepo.save(locatorOne);
        this.flatsRepo.save(flat);
        return locatorOne;
    }

    public Locator deleteLocator(Long locatorId) {
        Locator locator = this.locatorsRepo.findById(locatorId).orElse(null);
        Flat flat = this.flatsRepo.findById(locator.getFlat().getId()).orElse(null);
        List <Locator> locatorList = flat.getLocators();
        locatorList.remove(locator);
        flat.setLocators(locatorList);
        this.locatorsRepo.deleteById(locatorId);
        if (locatorList.size() == 0)
            flat.set_vacant(true);
        return locator;
    }

    public Optional<Locator> findOne(Long id) {
        return this.locatorsRepo.findById(id);
    }

    public void create_invoice(Long id, String name, String surname, String email, Long rentCost) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("faktura_"+id.toString()+".pdf"));
        //PdfWriter.getInstance(document, new FileOutputStream("faktura003.pdf"));
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
