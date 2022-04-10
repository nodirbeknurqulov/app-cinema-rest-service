package uz.pdp.appcinemarestservice.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appcinemarestservice.entity.*;
import uz.pdp.appcinemarestservice.entity.enums.Gender;
import uz.pdp.appcinemarestservice.entity.enums.RoleEnum;
import uz.pdp.appcinemarestservice.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    String initMode;

    final RoleRepository roleRepository;
    final UserRepository userRepository;
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
            RoleRepository repository, RoleRepository roleRepository, UserRepository userRepository, PriceCategoryRepository priceCategoryRepository,
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
        this.roleRepository = roleRepository;
//        this.repository = repository;
        this.userRepository = userRepository;
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

//            List<Row> rowList2 = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                rowList2.add(new Row(i + 1, zal2));
//            }
//            List<Row> rowList3 = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                rowList3.add(new Row(i + 1, zal3Vip));
//            }

            zal1.setRowList(rowList1);
            zal2.setRowList(rowList2);
            zal3Vip.setRowList(rowList3);

            // ZAL1, ZAL2, ZAL3VIP DB GA SAQLANADI
            hallRepository.save(zal1);
            hallRepository.save(zal2);
            hallRepository.save(zal3Vip);


            //attachment img
            Attachment movieImg = attachmentRepository.save(new Attachment("image", 1000L, "image/png", "dffdfddsf", new AttachmentContent()));
            // MOVIES

            Movie movie1 = new Movie(
                    "Batman",
                    "dsgagadsgasgasdg",
                    120,
                    null,
                    null,
                    LocalDate.now(),
                    50000.0,
                    null,
                    null,
                    12.0,
                    1.0
            );
            Movie movie2 = new Movie("Spiderman",
                    "dsgagadsgasgasdg",
                    120,
                    null,
                    null,
                    LocalDate.now(),
                    50000.0,
                    null,
                    null,
                    12.0,
                    7.0);
            Movie movie3 = new Movie("Superman","dsgagadsgasgasdg",
                    120,
                    null,
                    null,
                    LocalDate.now(),
                    50000.0,
                    null,
                    null,
                    12.0,
                    8.0);
            movieRepository.save(movie1);
            Movie spiderman = movieRepository.save(movie2);
            Movie superman = movieRepository.save(movie3);

            // SESSION DATES
            SessionDate may17 = new SessionDate(LocalDate.of(2022, 5, 17));

            SessionDate may18 = new SessionDate(LocalDate.of(2022, 5, 18));
            SessionDate may19 = new SessionDate(LocalDate.of(2022, 5, 19));
            sessionDateRepository.save(may17);
            sessionDateRepository.save(may18);
            sessionDateRepository.save(may19);

            //SESSION TIMES
            SessionTime hour11 = new SessionTime(LocalTime.of(11, 0));
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
            Role saveRoleAdmin = roleRepository.save(new Role(RoleEnum.ADMIN, null));

            Set<Role> rolesAdmin = new HashSet<>();
            rolesAdmin.add(saveRoleAdmin);

            userRepository.save(new User(
                    "Xayitboy",
                    "Egamberdiyev",
                    passwordEncoder.encode("1"),
                            "+998935249812",
                    "x",
                    LocalDate.of(2006, 6, 20),
                    Gender.MALE,
                    rolesAdmin
                    ));


            Role userRoleUser = roleRepository.save(new Role(RoleEnum.USER, null));
            Set<Role> rolesUser = new HashSet<>();
            rolesUser.add(userRoleUser);

            userRepository.save(new User(
                    "Abdulaziz",
                    "Zarifboyev",
                    passwordEncoder.encode("2"),
                    "+998935249342",
                    "a",
                    LocalDate.of(2001, 5, 20),
                    Gender.MALE,
                    rolesUser
            ));

        }


    }
}
