package com.example.qlbhbe.service.baocao;

import com.example.qlbhbe.dto.SanPhamDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PDF {
    private static Font catFont;

    @Autowired
    SanPhamReport sanPhamReport;
    private static Font font;

    static {
        try {
            catFont = new Font(BaseFont.createFont("C:\\CDTT\\BE\\arial.ttf"
                    , BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18,
                    Font.BOLD);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            font = new Font(BaseFont.createFont("C:\\CDTT\\BE\\arial.ttf"
                    , BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14,
                    Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Font redFont;

    static {
        try {
            redFont = new Font(BaseFont.createFont("C:\\CDTT\\BE\\arial.ttf"
                    , BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12,
                    Font.NORMAL, BaseColor.RED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Font subFont;

    static {
        try {
            subFont = new Font(BaseFont.createFont("C:\\CDTT\\BE\\arial.ttf"
                    , BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16,
                    Font.BOLD);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Font smallBold;

    static {
        try {
            smallBold = new Font(BaseFont.createFont("C:\\CDTT\\BE\\arial.ttf"
                    , BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12,
                    Font.BOLD);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Font small;

    static {
        try {
            small = new Font(BaseFont.createFont("C:\\CDTT\\BE\\arial.ttf"
                    , BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10,
                    Font.BOLD);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Font smallContent;

    static {
        try {
            smallContent = new Font(BaseFont.createFont("C:\\CDTT\\BE\\arial.ttf"
                    , BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10,
                    Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> exportSanPhamTonPDF(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            Document document = new Document();
            String FILE = "D:/SanPhamTonKho" + System.currentTimeMillis() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
//            addMetaData(document);
            addTitlePage(document, sanPhamDTO);
            addContent(document, sanPhamDTO);
            document.close();
            Map<String, String> tmp = new HashMap<>();
            tmp.put("path", FILE);
            return tmp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    private void addMetaData(Document document) {
//        document.addTitle("My first PDF");
//        document.addSubject("Using iText");
//        document.addKeywords("Java, PDF, iText");
//        document.addAuthor("Lars Vogel");
//        document.addCreator("Lars Vogel");
//    }

    private void setRow(Paragraph preface, String content, Font font, int style) {
        Paragraph tmp = new Paragraph(content, font);
        tmp.setAlignment(style);
        preface.add(tmp);
    }

    private Font font(int size, int style) throws IOException, DocumentException {
        return new Font(BaseFont.createFont("C:\\CDTT\\BE\\arial.ttf"
                , BaseFont.IDENTITY_H, BaseFont.EMBEDDED), size,
                style);
    }


    private void addTitlePage(Document document, SanPhamDTO sanPhamDTO)
            throws DocumentException, IOException {

        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        setRow(preface, "CÔNG TY CỔ PHẦN NÔNG NGHIỆP VÀ THỰC PHẨM LANG LIÊU", font, Element.ALIGN_LEFT);
        setRow(preface, "Mã số thuế: 0109736359", font, Element.ALIGN_LEFT);
        setRow(preface, "Địa Chỉ: Số nhà 2B, ngõ 389 Trương Định, Phường Tân Mai,\nQuận Hoàng Mai, Hà Nội", font, Element.ALIGN_LEFT);
        setRow(preface, "Bộ phận quản lý kho hàng", font, Element.ALIGN_LEFT);
        addEmptyLine(preface, 1);
        setRow(preface, "BÁO CÁO SẢN PHẦM TỒN KHO", font(18, Font.BOLD), Element.ALIGN_CENTER);
        setRow(preface, "Từ ngày 01/11/2021 đến ngày 30/11/2021 ", font(11, Font.ITALIC), Element.ALIGN_CENTER);
        addEmptyLine(preface, 2);
        setRow(preface, "Mã nhân viên:…………………………………………………………………………………", font(12, Font.NORMAL), Element.ALIGN_LEFT);
        setRow(preface, "Tên nhân viên:………………………………………………………………………………", font(12, Font.NORMAL), Element.ALIGN_LEFT);
        setRow(preface, "Ghi chú:………………………………………………………………………………………", font(12, Font.NORMAL), Element.ALIGN_LEFT);
        addEmptyLine(preface, 2);
        document.add(preface);
    }

    private void addContent(Document document, SanPhamDTO sanPhamDTO) throws Exception {
        Paragraph paragraph = new Paragraph();
        // add a table
        createTable(paragraph, sanPhamDTO);

        addEmptyLine(paragraph, 2);
        setRow(paragraph, "Hà Nội, ngày " + LocalDate.now().getDayOfMonth() + ", tháng " + LocalDate.now().getMonthValue() + ", năm " + LocalDate.now().getYear(), font(11, Font.ITALIC), Element.ALIGN_RIGHT);
        addEmptyLine(paragraph, 1);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.addCell(getCellNoBorder("Người tạo báo cáo \n (Ký, ghi rõ họ tên)", PdfPCell.ALIGN_CENTER, font(11, Font.NORMAL)));
        table.addCell(getCellNoBorder("Thủ kho \n (Ký, ghi rõ họ tên)", PdfPCell.ALIGN_CENTER, font(11, Font.NORMAL)));
        table.addCell(getCellNoBorder("Giám đốc \n (Ký, ghi rõ họ tên)", PdfPCell.ALIGN_CENTER, font(11, Font.NORMAL)));
        paragraph.add(table);
        document.add(paragraph);


    }

    public PdfPCell getCellNoBorder(String text, int alignment, Font font) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private void createTable(Paragraph paragraph, SanPhamDTO sanPhamDTO)
            throws Exception {
        PdfPTable table = new PdfPTable(7);
        PdfPCell c1 = null;
        setHeaderTable(table, "Mã sản phẩm", Element.ALIGN_CENTER, c1);
        setHeaderTable(table, "Tên Sản Phẩm", Element.ALIGN_CENTER, c1);
        setHeaderTable(table, "Giá Bán Niêm Yết", Element.ALIGN_CENTER, c1);
        setHeaderTable(table, "Giá Nhập Niêm Yết", Element.ALIGN_CENTER, c1);
        setHeaderTable(table, "Đơn vị", Element.ALIGN_CENTER, c1);
        setHeaderTable(table, "Số Lượng Tồn", Element.ALIGN_CENTER, c1);
        setHeaderTable(table, "Cửa Hàng", Element.ALIGN_CENTER, c1);
        table.setHeaderRows(1);
        List<SanPhamDTO> sanPhamDTOList = sanPhamReport.getSanPhamTon(sanPhamDTO);
        for (SanPhamDTO sanPhamDTO1 : sanPhamDTOList) {
            setContentTable(table, sanPhamDTO1.getMaSanPham(), Element.ALIGN_CENTER, c1);
            setContentTable(table, sanPhamDTO1.getTenSanPham(), Element.ALIGN_CENTER, c1);
            setContentTable(table,String.valueOf(sanPhamDTO1.getGiaBanNiemYet().intValue()), Element.ALIGN_CENTER, c1);
            setContentTable(table, String.valueOf(sanPhamDTO1.getGiaNhapNiemYet().intValue()), Element.ALIGN_CENTER, c1);
            setContentTable(table, sanPhamDTO1.getDonVi(), Element.ALIGN_CENTER, c1);
            setContentTable(table, String.valueOf(sanPhamDTO1.getSoLuongTon().intValue()), Element.ALIGN_CENTER, c1);
            setContentTable(table, sanPhamDTO1.getTenCuaHang(), Element.ALIGN_CENTER, c1);
        }
        paragraph.add(table);
    }

    private void setHeaderTable(PdfPTable table, String name, int style, PdfPCell c1) {
        c1 = new PdfPCell(new Phrase(name, small));
        c1.setHorizontalAlignment(style);
        table.addCell(c1);
    }

    private void setContentTable(PdfPTable table, String name, int style, PdfPCell c1) {
        c1 = new PdfPCell(new Phrase(name, smallContent));
        c1.setHorizontalAlignment(style);
        table.addCell(c1);
    }


    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
