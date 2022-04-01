package uz.pdp.appcinemarestservice.service;
// Nurkulov Nodirbek 3/31/2022  10:48 AM

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.appcinemarestservice.entity.Attachment;
import uz.pdp.appcinemarestservice.entity.AttachmentContent;
import uz.pdp.appcinemarestservice.entity.TransactionHistory;
import uz.pdp.appcinemarestservice.entity.Ticket;
import uz.pdp.appcinemarestservice.entity.enums.TicketStatus;
import uz.pdp.appcinemarestservice.payload.ApiResponse;
import uz.pdp.appcinemarestservice.projection.TicketProjection;
import uz.pdp.appcinemarestservice.repository.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository contentRepository;

    public ApiResponse purchaseTicket(Integer ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            Attachment attachment = getQrCodeAttachment(ticketId);
            ticket.setQr_code(attachment);
            ticket.setStatus(TicketStatus.PURCHASED);
            ticketRepository.save(ticket);
            return new ApiResponse("Ticket purchased!", true, attachment.getId());
        }
        return new ApiResponse("Ticket is not added!", false);
    }

    public Attachment getQrCodeAttachment(Integer ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            try {
                byte[] qrCodeImage = new byte[0];
                qrCodeImage = getQrCodeImage(ticket.getId().toString(), 200, 200);
                Attachment attachment = new Attachment();
                attachment.setOriginalFileName(ticket.getId().toString());
                attachment.setContentType("image/png");
                AttachmentContent attachmentContent = new AttachmentContent(qrCodeImage, attachment);
                attachmentRepository.save(attachment);
                contentRepository.save(attachmentContent);
                return attachment;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public byte[] getQrCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    public HttpEntity<?> getCurrentUserTickets(Integer userId) {
        List<TicketProjection> allTickets = ticketRepository.getTicketsByUserId(userId);
        return ResponseEntity.ok(allTickets);
    }

    public void changeTicketStatusToPurchase(Integer userId) {
        List<Ticket> newTicketsByUserIdAndStatus = ticketRepository.findByUserIdAndStatus(userId, TicketStatus.NEW);
        for (Ticket ticketsByUserIdAndStatus : newTicketsByUserIdAndStatus) {
            ticketsByUserIdAndStatus.setStatus(TicketStatus.PURCHASED);
        }
        ticketRepository.saveAll(newTicketsByUserIdAndStatus);
    }
}
