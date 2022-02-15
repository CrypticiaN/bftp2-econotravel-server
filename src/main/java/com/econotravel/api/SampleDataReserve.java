package com.econotravel.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SampleDataReserve {


    private final ReservaRepository reservaRepository;

    @Autowired
    public SampleDataReserve(ReservaRepository reservaRepository) {

        this.reservaRepository = reservaRepository;
    }

    @PostConstruct
    public void addSampleReservas() {
        if (reservaRepository.findAll().isEmpty()) {
            List<Reserva> reservas = List.of(
                    new Reserva("María Morales Montero",
                            "625258598",
                            2,
                            1,
                            "mariamm@gmail.com",
                            "Paseo en bicicleta por el Montseny",
                            "Somos celíacos"),
                    new Reserva("Helen Didsbury",
                            "652485785",
                            1,
                            2,
                            "helenhola@gmail.com",
                            "Arte en la montaña sagrada",
                            "Queremos Snacks saludables")
            );

            reservaRepository.saveAll(reservas);
        }
    }
}