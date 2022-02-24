package com.econotravel.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class Bftp2EconotravelServerApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ExperienceRepository experienceRepository;

    @BeforeEach
    void setUp() {
        experienceRepository.deleteAll();
    }

    @Test
    void returnsTheExistingExperiences() throws Exception {

        addSampleExperiences();

        mockMvc.perform(get("/api/experiences"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalTo("Experiencia 1: Paseo en bicicleta por el Montseny")))
                .andExpect(jsonPath("$[1].name", equalTo("Experiencia 2: Descubre la costa en barco de vela")))
                .andDo(print());
    }

    private void addSampleExperiences() {
        List<Experience> experiences = List.of(
                new Experience("Experiencia 1: Paseo en bicicleta por el Montseny", "../img/montseny.png", "Disfruta de un hermoso paseo en bicicleta por el increíble Parque Natural del Montseny. Una escapada veraniega perfecta para parejas, familias y amigos que nos permitirá conocer nuevos y sorprendentes lugares. El recorrido que os proponemos comienza con una larga subida con algunos descansos, combinando asfalto y pistas anchas, culminando en un mirador con magníficas vistas del Vallés y el mar. Desde aquí continuaremos la bajada combinando senderos, pistas y algún tramo de carretera y terreno mixto para visitar la zona de Santa Fe, donde descubriremos la Ermita y el Bosque de las Secuoyas. Para finalizar, acudiremos al restaurante María Rosa de Palautordera, donde disfrutaremos de una excelente comida casera con butifarra blanca y negra y munxetes del Montseny.", 250, "5h", "Actividad disponible para todas las edades. Disponemos de bicicletas accesibles para personas con movilidad reducida en el tren inferior así como sillines con capacidad para niños menores de 5 años (peso máximo 20kg).", "https://buy.stripe.com/5kAaGC3bKaFB80MaEH", "Montaña, bicicleta, excursión larga."),
                new Experience("Experiencia 2: Descubre la costa en barco de vela", "../img/barcoDeVela.png","Disfruta de un hermoso paseo acuático en barco de vela por la increíble costa de Barcelona. Una escapada veraniega apta incluso para los días más calurosos del año. Descubre los encantadores alrededores de la ciudad de Barcelona y visita desde el mar sus más impresionantes playas y calas. Comenzaremos la excursión en el Puerto de Barcelona, desde donde partiremos hacia el norte para visitar playas como la Mar Bella, la Playa de la Mora y la Playa de los Pescadores. A bordo de la embarcación podremos disfrutar de una selección de quesos y embutidos catalanes acompañada de cava brut y zumos de frutas. Asimismo, pararemos cerca de la Playa de Montgat para realizar una actividad de buceo de superficie que nos permitirá apreciar la diversidad de la fauna marítima local y su ecosistema. Finalizaremos la excursión en el mismo puerto del que partimos.", 280, "4h", "Actividad disponible para todas las edades. Pasarela para silla de ruedas disponible bajo reserva.", "https://buy.stripe.com/5kAaGC3bKaFB80MaEH", "Playa, barco, excursión larga.")
        );

        experienceRepository.saveAll(experiences);
    }

    @Test
    void createsNewExperiences() throws Exception {

        mockMvc.perform(post("/api/experiences/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Experiencia 1: Paseo en bicicleta por el Montseny\"}")
        ).andExpect(status().is(200));

        var experiences = experienceRepository.findAll();

        assertThat(experiences, contains(
                hasProperty("name", is("Paseo en bicicleta por el Montseny"))
        ));
    }

    @Test
    void allowsToDeleteAnExperience() throws Exception {
        Experience experience = experienceRepository.save(new Experience("Descubre la costa en barco de vela", "../img/montseny.png", "Disfruta de un hermoso paseo acuático en barco de vela por la increíble costa de Barcelona. Una escapada veraniega apta incluso para los días más calurosos del año. Descubre los encantadores alrededores de la ciudad de Barcelona y visita desde el mar sus más impresionantes playas y calas. Comenzaremos la excursión en el Puerto de Barcelona, desde donde partiremos hacia el norte para visitar playas como la Mar Bella, la Playa de la Mora y la Playa de los Pescadores. A bordo de la embarcación podremos disfrutar de una selección de quesos y embutidos catalanes acompañada de cava brut y zumos de frutas. Asimismo, pararemos cerca de la Playa de Montgat para realizar una actividad de buceo de superficie que nos permitirá apreciar la diversidad de la fauna marítima local y su ecosistema. Finalizaremos la excursión en el mismo puerto del que partimos.", 280, "4h", "Actividad disponible para todas las edades. Pasarela para silla de ruedas disponible bajo reserva.", "https://buy.stripe.com/5kAaGC3bKaFB80MaEH", "Playa, barco, excursión larga."));
        mockMvc.perform(delete("/api/experiences/delete/" + experience.getId()))
                .andExpect(status().is(200));

        assertThat(experienceRepository.findById(experience.getId()), equalTo(Optional.empty()));
    }
    @Test
    void allowsToModifyAnExperience() throws Exception {
        Experience experience = experienceRepository.save(new Experience("Descubre la costa en barco de vela", "../img/barcoDeVela.png", "Disfruta de un hermoso paseo acuático en barco de vela por la increíble costa de Barcelona. Una escapada veraniega apta incluso para los días más calurosos del año. Descubre los encantadores alrededores de la ciudad de Barcelona y visita desde el mar sus más impresionantes playas y calas. Comenzaremos la excursión en el Puerto de Barcelona, desde donde partiremos hacia el norte para visitar playas como la Mar Bella, la Playa de la Mora y la Playa de los Pescadores. A bordo de la embarcación podremos disfrutar de una selección de quesos y embutidos catalanes acompañada de cava brut y zumos de frutas. Asimismo, pararemos cerca de la Playa de Montgat para realizar una actividad de buceo de superficie que nos permitirá apreciar la diversidad de la fauna marítima local y su ecosistema. Finalizaremos la excursión en el mismo puerto del que partimos.",
                280,
                "4h",
                "Actividad disponible para todas las edades. Pasarela para silla de ruedas disponible bajo reserva.",
                "https://buy.stripe.com/5kAaGC3bKaFB80MaEH",
                "Playa, barco, excursión larga."));

        mockMvc.perform(put("/api/experiences/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"" + experience.getId() + "\", \"name\": \"Descubre la costa de Barcelona\"," + " \"image\": \"../img/montseny.png\"," +
                        " \"description\": \"Disfruta de un hermoso paseo acuático en barco de vela por la increíble costa de Barcelona\"," +
                        "\"price\": 200," +
                        "\"duration\": \"2h\"," +
                        "\"accessibility\": \"Pasarela para silla de ruedas disponible bajo reserva.\"," +
                        "\"payment\": \"https://buy.stripe.com/5kAaGC3bKaFB80MaEH\"," +
                        "\"tags\": \"Excursión larga\"}")
        ).andExpect(status().isOk());


        List<Experience> experiences = experienceRepository.findAll();

        assertThat(experiences, hasSize(1));
        assertThat(experiences.get(0).getName(), equalTo("Descubre la costa de Barcelona"));
        assertThat(experiences.get(0).getImage(), equalTo("../img/montseny.png"));
        assertThat(experiences.get(0).getDescription(), equalTo("Disfruta de un hermoso paseo acuático en barco de vela por la increíble costa de Barcelona"));
        assertThat(experiences.get(0).getPrice(), equalTo(200.0));
        assertThat(experiences.get(0).getDuration(), equalTo("2h"));
        assertThat(experiences.get(0).getAccessibility(), equalTo("Pasarela para silla de ruedas disponible bajo reserva."));
        assertThat(experiences.get(0).getPayment(), equalTo("https://buy.stripe.com/5kAaGC3bKaFB80MaEH"));
        assertThat(experiences.get(0).getTags(), equalTo("Excursión larga"));

    }


}
