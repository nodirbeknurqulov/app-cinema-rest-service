package uz.pdp.appcinemarestservice.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.appcinemarestservice.entity.Hall;
import uz.pdp.appcinemarestservice.entity.Row;
import uz.pdp.appcinemarestservice.repository.HallRepository;
import uz.pdp.appcinemarestservice.repository.RowRepository;

import java.util.ArrayList;
import java.util.List;

// Nurkulov Nodirbek 3/17/2022  7:52 AM
//@Component
public class DataLoader  {

//    @Value("${spring.sql.init.mode}")
//    String initMode;
//
//    @Autowired
//    RowRepository rowRepository;
//    @Autowired
//    HallRepository hallRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        if (initMode.equals("always")) {
//
//            // HALL OBJ CREATE QILINADI
//            Hall zal1 = new Hall("Zal 1");
//
//            //ROW LIST CREATE QILINADI
//            List<Row> rowList = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                rowList.add(new Row(i + 1, zal1));
//            }
//
//            zal1.setRows(rowList);
//
//            // ZAL1 DB GA SAQLANADI
//            hallRepository.save(zal1);
//
//        }
//
//    }
}
