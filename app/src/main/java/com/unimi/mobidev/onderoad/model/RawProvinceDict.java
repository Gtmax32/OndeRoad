package com.unimi.mobidev.onderoad.model;

import java.util.TreeMap;

/**
 * Created by Giuseppe on 22/09/2016.
 */

public class RawProvinceDict {

    private static final TreeMap<String, String> DICT;
    static{
        DICT = new TreeMap<>();
        DICT.put("Provincia di Agrigento", "Agrigento");
        DICT.put("Provincia di Alessandria", "Alessandria");
        DICT.put("Provincia di Ancona", "Ancona");
        DICT.put("Provincia di Arezzo", "Arezzo");
        DICT.put("Provincia di Ascoli Piceno", "Ascoli Piceno");
        DICT.put("Provincia di Asti", "Asti");
        DICT.put("Provincia di Avellino", "Avellino");
        DICT.put("Città Metropolitana di Bari", "Bari");
        DICT.put("Provincia di Barletta-Andria-Trani", "Barletta-Andria-Trani");
        DICT.put("Provincia di Belluno", "Belluno");
        DICT.put("Provincia di Benevento", "Benevento");
        DICT.put("Provincia di Bergamo", "Bergamo");
        DICT.put("Provincia di Biella", "Biella");
        DICT.put("Città Metropolitana di Bologna", "Bologna");
        DICT.put("Provincia di Bolzano", "Bolzano");
        DICT.put("Provincia di Brescia", "Brescia");
        DICT.put("Provincia di Brindisi", "Brindisi");
        DICT.put("Provincia di Cagliari", "Cagliari");
        DICT.put("Provincia di Caltanissetta", "Caltanissetta");
        DICT.put("Provincia di Campobasso", "Campobasso");
        DICT.put("Provincia di Carbonia-Iglesias", "Carbonia-Iglesias");
        DICT.put("Provincia di Caserta", "Caserta");
        DICT.put("Città Metropolitana di Catania", "Catania");
        DICT.put("Provincia di Catanzaro", "Catanzaro");
        DICT.put("Provincia di Chieti", "Chieti");
        DICT.put("Provincia di Como", "Como");
        DICT.put("Provincia di Cosenza", "Cosenza");
        DICT.put("Provincia di Cremona", "Cremona");
        DICT.put("Provincia di Crotone", "Crotone");
        DICT.put("Provincia di Cuneo", "Cuneo");
        DICT.put("Provincia di Enna", "Enna");
        DICT.put("Provincia di Fermo", "Fermo");
        DICT.put("Provincia di Ferrara", "Ferrara");
        DICT.put("Città Metropolitana di Firenze", "Firenze");
        DICT.put("Provincia di Foggia", "Foggia");
        DICT.put("Provincia di Forlì-Cesena", "Forlì-Cesena");
        DICT.put("Provincia di Frosinone", "Frosinone");
        DICT.put("Provincia di Città Metropolitana di Genova", "Genova");
        DICT.put("Provincia di Gorizia", "Gorizia");
        DICT.put("Provincia di Grosseto", "Grosseto");
        DICT.put("Provincia di Imperia", "Imperia");
        DICT.put("Provincia di Isernia", "Isernia");
        DICT.put("Provincia dell'Aquila", "L'Aquila");
        DICT.put("Provincia della Spezia", "La Spezia");
        DICT.put("Provincia di Latina", "Latina");
        DICT.put("Provincia di Lecce", "Lecce");
        DICT.put("Provincia di Lecco", "Lecco");
        DICT.put("Provincia di Livorno", "Livorno");
        DICT.put("Provincia di Lodi", "Lodi");
        DICT.put("Provincia di Lucca", "Lucca");
        DICT.put("Provincia di Macerata", "Macerata");
        DICT.put("Provincia di Mantova", "Mantova");
        DICT.put("Provincia di Massa e Carrara", "Massa-Carrara");
        DICT.put("Provincia di Matera", "Matera");
        DICT.put("Provincia del Medio Campidano", "Medio Campidano");
        DICT.put("Città Metropolitana di Messina", "Messina");
        DICT.put("Città Metropolitana di Milano", "Milano");
        DICT.put("Provincia di Modena", "Modena");
        DICT.put("Provincia di Monza e della Brianza", "Monza e della Brianza");
        DICT.put("Città Metropolitana di Napoli", "Napoli");
        DICT.put("Provincia di Novara", "Novara");
        DICT.put("Provincia di Nuoro", "Nuoro");
        DICT.put("Provincia di Ogliastra", "Ogliastra");
        DICT.put("Provincia di Olbia-Tempio", "Olbia-Tempio");
        DICT.put("Provincia di Oristano", "Oristano");
        DICT.put("Provincia di Padova", "Padova");
        DICT.put("Città Metropolitana di Palermo", "Palermo");
        DICT.put("Provincia di Palermo", "Palermo");
        DICT.put("Provincia di Parma", "Parma");
        DICT.put("Provincia di Pavia", "Pavia");
        DICT.put("Provincia di Perugia", "Perugia");
        DICT.put("Provincia di Pesaro e Urbino", "Pesaro e Urbino");
        DICT.put("Provincia di Pescara", "Pescara");
        DICT.put("Provincia di Piacenza", "Piacenza");
        DICT.put("Provincia di Pisa", "Pisa");
        DICT.put("Provincia di Pistoia", "Pistoia");
        DICT.put("Provincia di Pordenone", "Pordenone");
        DICT.put("Provincia di Potenza", "Potenza");
        DICT.put("Provincia di Prato", "Prato");
        DICT.put("Provincia di Ragusa", "Ragusa");
        DICT.put("Provincia di Ravenna", "Ravenna");
        DICT.put("Provincia di Reggio Calabria", "Reggio Calabria");
        DICT.put("Provincia di Reggio Emilia", "Reggio Emilia");
        DICT.put("Provincia di Rieti", "Rieti");
        DICT.put("Provincia di Rimini", "Rimini");
        DICT.put("Città Metropolitana di Roma", "Roma");
        DICT.put("Provincia di Rovigo", "Rovigo");
        DICT.put("Provincia di Salerno", "Salerno");
        DICT.put("Provincia di Sassari", "Sassari");
        DICT.put("Provincia di Savona", "Savona");
        DICT.put("Provincia di Siena", "Siena");
        DICT.put("Provincia di Siracusa", "Siracusa");
        DICT.put("Provincia di Sondrio", "Sondrio");
        DICT.put("Provincia di Taranto", "Taranto");
        DICT.put("Provincia di Teramo", "Teramo");
        DICT.put("Provincia di Terni", "Terni");
        DICT.put("Città Metropolitana di Torino", "Torino");
        DICT.put("Provincia di Trapani", "Trapani");
        DICT.put("Provincia di Trento", "Trento");
        DICT.put("Provincia di Treviso", "Treviso");
        DICT.put("Provincia di Trieste", "Trieste");
        DICT.put("Provincia di Udine", "Udine");
        DICT.put("Valle d'Aosta", "Valle d'Aosta");
        DICT.put("Provincia di Varese", "Varese");
        DICT.put("Città Metropolitana di Venezia", "Venezia");
        DICT.put("Provincia del Verbano-Cusio-Ossola", "Verbano-Cusio-Ossola");
        DICT.put("Provincia di Vercelli", "Vercelli");
        DICT.put("Provincia di Verona", "Verona");
        DICT.put("Provincia di Vibo Valentia", "Vibo Valentia");
        DICT.put("Provincia di Vicenza", "Vicenza");
        DICT.put("Provincia di Viterbo", "Viterbo");
    }

    public static String getValue(String key){
        return DICT.get(key);
    }
}
