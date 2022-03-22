package com.javachinna.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.javachinna.model.Formation;
import com.javachinna.model.PartnerInstitution;
import com.javachinna.model.User;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class exportPdf {




    public void pdfReader(Formation f , User user )
    {


        try
        {

            String Nom="nom:"+user.getLastName();
            String Prenom="Prenom:"+user.getFirstName();
            String Email="Email:"+user.getEmail();
            String Formation="Formation:"+f.getTitle();
            //Read file using PdfReader
            PdfReader pdfReader = new PdfReader("/Users/macos/IdeaProjects/springPidev/src/main/resources/static/Certif/cc.pdf");

            //Modify file using PdfReader
            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("/Users/macos/IdeaProjects/springPidev/src/main/resources/static/Certif/C"+user.getId()+".pdf"));


            Image image = Image.getInstance("/Users/macos/IdeaProjects/springPidev/src/main/resources/static/mybadges/goldbadge.png");
            Image image2 = Image.getInstance("/Users/macos/IdeaProjects/springPidev/src/main/resources/static/img/img.png");
            image.scaleAbsolute(100, 100);
            image.setAbsolutePosition(450f, 450f);


            image2.scaleAbsolute(90, 90);
            image2.setAbsolutePosition(710f, 480f);




            PdfContentByte canvas = pdfStamper.getOverContent(1);
            canvas.addImage(image);
            canvas.addImage(image2);

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(20);

            Paragraph paragraph = new Paragraph("Certificat of Achivement ", fontTitle);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);

            Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
            fontParagraph.setSize(16);

            Paragraph paragraph2 = new Paragraph( Nom, fontParagraph);
            paragraph2.setAlignment(Paragraph.ALIGN_CENTER);

            Paragraph paragraph3 = new Paragraph(Prenom, fontParagraph);
            paragraph3.setAlignment(Paragraph.ALIGN_CENTER);

            Paragraph paragraph4 = new Paragraph(Email, fontParagraph);
            paragraph4.setAlignment(Paragraph.ALIGN_CENTER);

            Paragraph paragraph5 = new Paragraph(Formation, fontParagraph);
            paragraph5.setAlignment(Paragraph.ALIGN_CENTER);



            ColumnText.showTextAligned(canvas, Element.BODY, paragraph, 450, 350, 0);
            ColumnText.showTextAligned(canvas, Element.BODY, paragraph2, 450, 320, 0);
            ColumnText.showTextAligned(canvas, Element.BODY, paragraph3, 450, 300, 0);
            ColumnText.showTextAligned(canvas, Element.BODY, paragraph4, 450, 280, 0);
            ColumnText.showTextAligned(canvas, Element.BODY, paragraph5, 450, 260, 0);
/*
            Font font= FontFactory.getFont(FontFactory.COURIER,12,BaseColor.LIGHT_GRAY);
            for(int i=1; i< pdfReader.getNumberOfPages(); i++)
            {
                PdfContentByte content = pdfStamper.getUnderContent(i);

                Paragraph para = new Paragraph("Formation List ",font);
                para.setAlignment(Element.ALIGN_CENTER);
                content.add();
                content.addImage(image);
            }


 */

            pdfStamper.close();

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }





    public static ByteArrayInputStream FormationPDFReport(List<Formation> formations) throws IOException {
        Document document=new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document,out);
            document.open();
            //add text to pdf file
            Font font= FontFactory.getFont(FontFactory.COURIER,12,BaseColor.LIGHT_GRAY);
            Paragraph para = new Paragraph("Formation List ",font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table=new PdfPTable(9);
            //make the columns
            Stream.of("Id","Title","domain","Frais","Niveau","startDay","EndDay","nbrHeurs","NbrMax").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headfont= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.YELLOW);
                header.setBorderWidth(12);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(headerTitle,headfont));
                table.addCell(header);


            });

            for (Formation f : formations){


                PdfPCell idCell = new PdfPCell(new Phrase((f.getIdFormation().toString())));
                idCell.setPaddingLeft(1);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(idCell);

                PdfPCell nameCell = new PdfPCell(new Phrase(f.getTitle()));
                nameCell.setPaddingLeft(1);
                nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(nameCell);

                PdfPCell countryCell = new PdfPCell(new Phrase(f.getDomain().toString()));
                countryCell.setPaddingLeft(1);
                countryCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                countryCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(countryCell);

                PdfPCell areaCell = new PdfPCell(new Phrase(String.valueOf(f.getFrais())));
                areaCell.setPaddingLeft(1);
                areaCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                areaCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(areaCell);

                PdfPCell specialtyCell = new PdfPCell(new Phrase(String.valueOf(f.getLevel())));
                specialtyCell.setPaddingLeft(1);
                specialtyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                specialtyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(specialtyCell);

                PdfPCell start = new PdfPCell(new Phrase(f.getStart().toString()));
                start.setPaddingLeft(1);
                start.setVerticalAlignment(Element.ALIGN_MIDDLE);
                start.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(start);

                PdfPCell end = new PdfPCell(new Phrase(f.getEnd().toString()));
                end.setPaddingLeft(1);
                end.setVerticalAlignment(Element.ALIGN_MIDDLE);
                end.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(end);



                PdfPCell CapacityReceptionCell = new PdfPCell(new Phrase(String.valueOf(f.getNbrHeures())));
                CapacityReceptionCell.setPaddingLeft(1);
                CapacityReceptionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                CapacityReceptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(CapacityReceptionCell);

                PdfPCell max = new PdfPCell(new Phrase(String.valueOf(f.getNbrMaxParticipant())));
                max.setPaddingLeft(1);
                max.setVerticalAlignment(Element.ALIGN_MIDDLE);
                max.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(max);


            }
            // Creating an ImageData object
            String url = "./src/main/resources/static/img/QRCode.png";
            Image image = Image.getInstance(url);
            document.add(image);


            document.add(table);
            document.close();

        } catch ( DocumentException | MalformedURLException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
    public  ByteArrayInputStream universityPDFReport(List<PartnerInstitution> partnerInstitutions) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            //add text to pdf file
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph("University List ", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(9);
            //make the columns
            Stream.of("Id", "Name", "Country", "Area", "Specialty", "Language", "Available", "CapacityReception", "Description").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headfont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.YELLOW);
                header.setBorderWidth(15);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(headerTitle, headfont));
                table.addCell(header);


            });

            for (PartnerInstitution university : partnerInstitutions) {

                //Id university
                PdfPCell idCell = new PdfPCell(new Phrase((university.getIdPartner().toString())));
                idCell.setPaddingLeft(1);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(idCell);
                //Name university
                PdfPCell nameCell = new PdfPCell(new Phrase(university.getName()));
                nameCell.setPaddingLeft(1);
                nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(nameCell);

                PdfPCell countryCell = new PdfPCell(new Phrase(university.getCountry()));
                countryCell.setPaddingLeft(1);
                countryCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                countryCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(countryCell);

                PdfPCell areaCell = new PdfPCell(new Phrase(String.valueOf(university.getGeographicalArea())));
                areaCell.setPaddingLeft(1);
                areaCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                areaCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(areaCell);

                PdfPCell specialtyCell = new PdfPCell(new Phrase(String.valueOf(university.getSpecial())));
                specialtyCell.setPaddingLeft(1);
                specialtyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                specialtyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(specialtyCell);

                PdfPCell LanguageCell = new PdfPCell(new Phrase(university.getLanguage()));
                LanguageCell.setPaddingLeft(1);
                LanguageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                LanguageCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(LanguageCell);

                PdfPCell AvailableCell = new PdfPCell(new Phrase(String.valueOf(university.isActive())));
                AvailableCell.setPaddingLeft(1);
                AvailableCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                AvailableCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(AvailableCell);

                PdfPCell CapacityReceptionCell = new PdfPCell(new Phrase(university.getCapacityReception()));
                CapacityReceptionCell.setPaddingLeft(1);
                CapacityReceptionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                CapacityReceptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(CapacityReceptionCell);

                PdfPCell descriptionCell = new PdfPCell(new Phrase(university.getDescription()));
                descriptionCell.setPaddingLeft(1);
                descriptionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                descriptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(descriptionCell);

            }

            document.add(table);
            document.close();


        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }






}
