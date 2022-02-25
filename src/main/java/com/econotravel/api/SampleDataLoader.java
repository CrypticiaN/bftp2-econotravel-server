package com.econotravel.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SampleDataLoader {


    private final ExperienceRepository experienceRepository;

    @Autowired
    public SampleDataLoader(ExperienceRepository experienceRepository) {

        this.experienceRepository = experienceRepository;
    }

    @PostConstruct
    public void addSampleMovies() {
        if (experienceRepository.findAll().isEmpty()) {
            List<Experience> experiences = List.of(
                    new Experience("Paseo en bicicleta por el Montseny",
                            "https://somosrecycling.es/images/blog-recycling/santa_fe_bici_otono.jpg",
                            "Disfruta de un hermoso paseo en bicicleta por el increíble Parque Natural del Montseny. Una escapada veraniega perfecta para parejas, familias y amigos que nos permitirá conocer nuevos y sorprendentes lugares. El recorrido que os proponemos comienza con una larga subida con algunos descansos, combinando asfalto y pistas anchas, culminando en un mirador con magníficas vistas del Vallés y el mar. Desde aquí continuaremos la bajada combinando senderos, pistas y algún tramo de carretera y terreno mixto para visitar la zona de Santa Fe, donde descubriremos la Ermita y el Bosque de las Secuoyas. Para finalizar, acudiremos al restaurante María Rosa de Palautordera, donde disfrutaremos de una excelente comida casera con butifarra blanca y negra y munxetes del Montseny.",
                            250,
                            "5h",
                            "Actividad disponible para todas las edades. Disponemos de bicicletas accesibles para personas con movilidad reducida en el tren inferior así como sillines con capacidad para niños menores de 5 años (peso máximo 20kg).",
                            "https://buy.stripe.com/5kA2a68w47tp0yk28f",
                            "Montaña, bicicleta, excursión larga."),
                    new Experience("Descubre la costa en barco de vela",
                            "https://fever.imgix.net/plan/photo/3ae1724a-3d30-11ea-bf03-06551cb39bc6.jpg?w=550&h=550&auto=format&fm=jpg",
                            "Disfruta de un hermoso paseo acuático en barco de vela por la increíble costa de Barcelona. Una escapada veraniega apta incluso para los días más calurosos del año. Descubre los encantadores alrededores de la ciudad de Barcelona y visita desde el mar sus más impresionantes playas y calas. Comenzaremos la excursión en el Puerto de Barcelona, desde donde partiremos hacia el norte para visitar playas como la Mar Bella, la Playa de la Mora y la Playa de los Pescadores. A bordo de la embarcación podremos disfrutar de una selección de quesos y embutidos catalanes acompañada de cava brut y zumos de frutas. Asimismo, pararemos cerca de la Playa de Montgat para realizar una actividad de buceo de superficie que nos permitirá apreciar la diversidad de la fauna marítima local y su ecosistema. Finalizaremos la excursión en el mismo puerto del que partimos.",
                            280,
                            "4h",
                            "Actividad disponible para todas las edades. Pasarela para silla de ruedas disponible bajo reserva.",
                            "https://buy.stripe.com/cN26qmbIg00X4OAbIO",
                            "Playa, barco, excursión larga."),
                    new Experience("Descubre la Barcelona Modernista de noche",
                            "https://www.santpaubarcelona.org/sites/default/files/styles/header_image/public/disseny_sense_titol_2.png?itok=55eBz6oq",
                            "Desplazarse a pie es una de las mejores formas de descubrir las maravillas modernistas que se esconden en el barcelonés distrito del Eixample, ubicado en el centro de la ciudad. En esta excursión de cuatro horas, descubriremos los principales emblemas del modernismo y visitaremos los templos y edificios más célebres del arquitecto Gaudí. El tour incluye visita guiada al interior de la Casa Batlló y la Sagrada Familia, así como parada para cenar en el restaurante típico catalán Can Masiá, donde disfrutaremos de las mejores carnes de la región acompañadas de vinos de las tierras del Baix Empordá. El restaurante también ofrece opciones vegetarianas y veganas así como menú para niños. Cava aparte.",
                            200,
                            "4h",
                            "Actividad disponible para todas las edades. Accesibilidad garantizada para sillas de ruedas.",
                            "https://buy.stripe.com/fZe3ea13CdRN6WI005",
                            "Ciudad, a pie, excursión larga."),
                    new Experience("Montbaig. Del huerto a la mesa",
                            "https://www.conmishijos.com/uploads/Familias/huerto-art.jpg",
                            "Comparte con los más pequeños las maravillas de la naturaleza. En esta excursión breve de tres horas, podréis plantar vuestras propias hortalizas y verduras en el huerto de Can Gilabert, ubicado en el corazón del Montbaig. Durante una hora y media, aprenderéis a cuidar de vuestro propio huerto, plantaréis y abonaréis vuestras propias plantas, y también recogeréis parte de los ingredientes que los expertos chefs de Can Gilabert utilizarán para preparar la posterior cena, donde disfrutaréis de la experiencia de consumir productos recién recogidos de la tierra con vuestras propias manos. Actividad ideal para familiarizar a los más pequeños de la familia con el estilo de vida rural y la agricultura sostenible. Asimismo, obtendréis como obsequio una botella de aceite virgen extra cultivado y preparado en Can Gilabert, obra de la familia Gilabert, que lleva más de 80 años cultivando olivos.",
                            145,
                            "3h",
                            "Actividad disponible para todas las edades. El acceso al huerto en silla de ruedas está garantizado.",
                            "https://buy.stripe.com/aEUdSOh2A6pldl66os",
                            "Montaña, a pie, excursión corta."),
                    new Experience("Arte en la montaña sagrada",
                            "https://3.bp.blogspot.com/_DCQhVsheLew/TJmZXcuyfvI/AAAAAAAABE8/4XnHpb12-rY/s1600/800px-MontserratMonastery01.jpg",
                            "Disfruta de la impresionante Montaña de Montserrat donde encontraréis el Museo con las salas modernistas de Puig i Cadafalch, lugar que acoge colecciones de gran valor en el corazón de la emblemática montaña. En el Museo encontraréis pinturas del Renacimiento y del Barroco que conviven con autores de los siglos XIX y XX, objetos del Próximo Oriente y orfebrería. Además de disfrutar de un paseo guiado por la montaña y el Museo, esta actividad incluye tentempié variado acompañado de vino espumoso, cava brut o refresco.",
                            125,
                            "2h",
                            "Actividad disponible para todas las edades. El acceso al museo y al restaurante en silla de ruedas está garantizado.",
                            "https://buy.stripe.com/5kAaGC3bKaFB80MaEH",
                            "Montaña, a pie, excursión corta."),
                    new Experience("VALL DE NÚRIA: Un valle único",
                            "https://www.lugaresdenieve.com/sites/default/files/vall-nuria_19.jpg",
                            "Vall de Núria es un paraíso excepcional tanto para grandes como para pequeños. No hace falta subir a los picos más altos que rodean el valle para poder observar el paisaje idílico. La gran mayoría de caminos y miradores de Vall de Núria están al alcance de todo el mundo. Para disfrutar del invierno y que los pequeños y pequeñas puedan jugar con la nieve.",
                            125,
                            "5h",
                            "Actividad disponible para todas las edades. El acceso a la montaña y al restaurante en silla de ruedas está garantizado.",
                            "https://buy.stripe.com/5kAaGC3bKaFB80MaEH",
                            "Montaña, a pie, excursión corta, nieve, esquí")

            );

            experienceRepository.saveAll(experiences);
        }
    }
}
