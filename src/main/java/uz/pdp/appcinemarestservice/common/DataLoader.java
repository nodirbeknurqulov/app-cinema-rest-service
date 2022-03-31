package uz.pdp.appcinemarestservice.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.appcinemarestservice.entity.*;
import uz.pdp.appcinemarestservice.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    String initMode;

    final PriceCategoryRepository priceCategoryRepository;
    final SeatRepository seatRepository;
    final RowRepository rowRepository;
    final HallRepository hallRepository;
    final AttachmentRepository attachmentRepository;
    final MovieRepository movieRepository;
    final SessionDateRepository sessionDateRepository;
    final SessionTimeRepository sessionTimeRepository;
    final MovieAnnouncementRepository movieAnnouncementRepository;
    final SessionRepository movieSessionRepository;

    public DataLoader(
            PriceCategoryRepository priceCategoryRepository,
            SeatRepository seatRepository,
            RowRepository rowRepository,
            HallRepository hallRepository,
            AttachmentRepository attachmentRepository,
            MovieRepository movieRepository,
            SessionDateRepository sessionDateRepository,
            SessionTimeRepository sessionTimeRepository,
            MovieAnnouncementRepository movieAnnouncementRepository,
            SessionRepository movieSessionRepository
    ) {
        this.priceCategoryRepository = priceCategoryRepository;
        this.seatRepository = seatRepository;
        this.rowRepository = rowRepository;
        this.hallRepository = hallRepository;
        this.attachmentRepository = attachmentRepository;
        this.movieRepository = movieRepository;
        this.sessionDateRepository = sessionDateRepository;
        this.sessionTimeRepository = sessionTimeRepository;
        this.movieAnnouncementRepository = movieAnnouncementRepository;
        this.movieSessionRepository = movieSessionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {

            //PRICE CATEGORY LIST CREATE QILINADI
            List<PriceCategory> priceCategories = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                priceCategories.add(new PriceCategory(
                        "Cat" + (i + 1),
                        (2.0 * i),
                        "#" + i + i + i
                ));
            }
            priceCategoryRepository.saveAll(priceCategories);


            // HALL OBJ CREATE QILINADI
            Hall zal1 = new Hall("Zal 1");
            Hall zal2 = new Hall("Zal 2");
            Hall zal3Vip = new Hall("Zal 3 VIP", 20.0);


            //ROW LIST CREATE QILINADI
            //================ZAL1 ROWS=====================
            List<Row> rowList1 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Row rowN = new Row(i + 1, zal1);
                //SEAT LIST CREATE QILINADI
                List<Seat> seats = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    int priceCatIndex = j < 3 ? 1 : j < 6 ? 2 : 3;
                    seats.add(new Seat(j + 1, rowN, priceCategories.get(priceCatIndex)));
                }
                rowN.setSeatList(seats);
                rowList1.add(rowN);
            }
            //=================ZAL2 ROWS====================
            List<Row> rowList2 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Row rowN = new Row(i + 1, zal2);
                //SEAT LIST CREATE QILINADI
                List<Seat> seats = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    int priceCatIndex = j < 3 ? 1 : j < 6 ? 2 : 3;
                    seats.add(new Seat(j + 1, rowN, priceCategories.get(priceCatIndex)));
                }
                rowN.setSeatList(seats);
                rowList2.add(rowN);
            }
            //=================ZAL3VIP ROWS====================
            List<Row> rowList3 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Row rowN = new Row(i + 1, zal3Vip);
                //SEAT LIST CREATE QILINADI
                List<Seat> seats = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    int priceCatIndex = j < 3 ? 1 : j < 6 ? 2 : 3;
                    seats.add(new Seat(j + 1, rowN, priceCategories.get(priceCatIndex)));
                }
                rowN.setSeatList(seats);
                rowList3.add(rowN);
            }

            zal1.setRowList(rowList1);
            zal2.setRowList(rowList2);
            zal3Vip.setRowList(rowList3);

            // ZAL1, ZAL2, ZAL3VIP DB GA SAQLANADI
            hallRepository.save(zal1);
            hallRepository.save(zal2);
            hallRepository.save(zal3Vip);

            //attachment img
            Attachment movieImg = attachmentRepository.save(new Attachment("movieImg",10000L,"image/jpg",null,new AttachmentContent()));

//             MOVIES
            Movie movie1 = new Movie("Batman", "dsgagadsgasgasdg", 120, 50000.0, movieImg, movieImg, LocalDate.now(), 10000000.0, 50.0);
            Movie movie2 = new Movie("Spiderman", "zxcvzxcv cbvxvxcbxxcv dgfshdfghdfghfg", 110, 40000.0, movieImg, movieImg, LocalDate.now(), 9000000.0,  40.0);
            Movie movie3 = new Movie("Superman", "xzcvzcx teyrtyuru bxcxbvcx", 90, 45000.0, movieImg, movieImg, LocalDate.now(), 12000000.0,  60.0);
            Movie movie = movieRepository.save(movie1);
            Movie spiderman = movieRepository.save(movie2);
            Movie superman = movieRepository.save(movie3);

            SessionDate may17 = new SessionDate(LocalDate.of(2022,5,19));
            SessionDate may18 = new SessionDate(LocalDate.of(2022, 5, 18));
            SessionDate may19 = new SessionDate(LocalDate.of(2022, 5, 19));

            sessionDateRepository.save(may17);
            sessionDateRepository.save(may18);
            sessionDateRepository.save(may19);

            //SESSION TIMES
            SessionTime hour11 = new SessionTime();
            SessionTime hour13 = new SessionTime(LocalTime.of(13, 0));
            SessionTime hour15 = new SessionTime(LocalTime.of(15, 0));
            SessionTime hour18 = new SessionTime(LocalTime.of(18, 0));
            sessionTimeRepository.save(hour11);
            sessionTimeRepository.save(hour13);
            sessionTimeRepository.save(hour15);
            sessionTimeRepository.save(hour18);

            //MOVIE ANNOUNCEMENTS
            MovieAnnouncement batmanAfisha = movieAnnouncementRepository.save(
                    new MovieAnnouncement(movie1, true));
            MovieAnnouncement spidermanAfisha = movieAnnouncementRepository.save(
                    new MovieAnnouncement(spiderman, true));
            MovieAnnouncement supermanAfisha = movieAnnouncementRepository.save(
                    new MovieAnnouncement(superman, true));

            // MOVIE SESSIONS
            movieSessionRepository.save(
                    new MovieSession(
                            batmanAfisha,
                            zal1,
                            may18,
                            hour11,
                            hour13
                    )
            );
            movieSessionRepository.save(
                    new MovieSession(
                            batmanAfisha,
                            zal1,
                            may18,
                            hour15,
                            hour18
                    )
            );
            movieSessionRepository.save(
                    new MovieSession(
                            spidermanAfisha,
                            zal3Vip,
                            may18,
                            hour15,
                            hour18
                    )
            );

            movieSessionRepository.save(
                    new MovieSession(
                            spidermanAfisha,
                            zal2,
                            may19,
                            hour11,
                            hour13
                    )
            );
            movieSessionRepository.save(
                    new MovieSession(
                            spidermanAfisha,
                            zal2,
                            may19,
                            hour15,
                            hour18
                    )
            );

            movieSessionRepository.save(
                    new MovieSession(
                            supermanAfisha,
                            zal3Vip,
                            may19,
                            hour11,
                            hour13
                    )
            );
        }
    }
}
