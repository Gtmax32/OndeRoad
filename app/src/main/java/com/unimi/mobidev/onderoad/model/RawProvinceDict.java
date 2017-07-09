package com.unimi.mobidev.onderoad.model;

import java.util.TreeMap;

/**
 * Created by Giuseppe on 22/09/2016.
 */

public class RawProvinceDict {

    private static final TreeMap<String, String> DICT;
    static{
        DICT = new TreeMap<>();
        DICT.put("Agrigento", "Agrigento");
        DICT.put("Alessandria", "Alessandria");
        DICT.put("Ancona", "Ancona");
        DICT.put("Aosta", "Aosta");
        DICT.put("Arezzo", "Arezzo");
        DICT.put("Ascoli Piceno", "Ascoli Piceno");
        DICT.put("Asti", "Asti");
        DICT.put("Avellino", "Avellino");
        DICT.put("Città Metropolitana di Bari", "Bari");
        DICT.put("Provincia di Barletta-Andria-Trani", "Barletta-Andria-Trani");
        DICT.put("Belluno", "Belluno");
        DICT.put("Benevento", "Benevento");
        DICT.put("Bergamo", "Bergamo");
        DICT.put("Biella", "Biella");
        DICT.put("Città Metropolitana di Bologna", "Bologna");
        DICT.put("Bolzano", "Bolzano");
        DICT.put("Brescia", "Brescia");
        DICT.put("Brindisi", "Brindisi");
        DICT.put("Cagliari", "Cagliari");
        DICT.put("Caltanissetta", "Caltanissetta");
        DICT.put("Campobasso", "Campobasso");
        DICT.put("Carbonia-Iglesias", "Carbonia-Iglesias");
        DICT.put("Caserta", "Caserta");
        DICT.put("Città Metropolitana di Catania", "Catania");
        DICT.put("Catanzaro", "Catanzaro");
        DICT.put("Chieti", "Chieti");
        DICT.put("Provincia di Como", "Como");
        DICT.put("Cosenza", "Cosenza");
        DICT.put("Cremona", "Cremona");
        DICT.put("Crotone", "Crotone");
        DICT.put("Cuneo", "Cuneo");
        DICT.put("Enna", "Enna");
        DICT.put("Provincia di Fermo", "Fermo");
        DICT.put("Ferrara", "Ferrara");
        DICT.put("Città Metropolitana di Firenze", "Firenze");
        DICT.put("Foggia", "Foggia");
        DICT.put("Forlì-Cesena", "Forlì-Cesena");
        DICT.put("Frosinone", "Frosinone");
        DICT.put("Città Metropolitana di Genova", "Genova");
        DICT.put("Gorizia", "Gorizia");
        DICT.put("Grosseto", "Grosseto");
        DICT.put("Imperia", "Imperia");
        DICT.put("Isernia", "Isernia");
        DICT.put("L'Aquila", "L'Aquila");
        DICT.put("La Spezia", "La Spezia");
        DICT.put("Latina", "Latina");
        DICT.put("Lecce", "Lecce");
        DICT.put("Lecco", "Lecco");
        DICT.put("Livorno", "Livorno");
        DICT.put("Lodi", "Lodi");
        DICT.put("Lucca", "Lucca");
        DICT.put("Macerata", "Macerata");
        DICT.put("Mantova", "Mantova");
        DICT.put("Massa-Carrara", "Massa-Carrara");
        DICT.put("Matera", "Matera");
        DICT.put("Medio Campidano", "Medio Campidano");
        DICT.put("Città Metropolitana di Messina", "Messina");
        DICT.put("Città Metropolitana di Milano", "Milano");
        DICT.put("Modena", "Modena");
        DICT.put("Provincia di Monza e della Brianza", "Monza e della Brianza");
        DICT.put("Città Metropolitana di Napoli", "Napoli");
        DICT.put("Novara", "Novara");
        DICT.put("Nuoro", "Nuoro");
        DICT.put("Ogliastra", "Ogliastra");
        DICT.put("Olbia-Tempio", "Olbia-Tempio");
        DICT.put("Oristano", "Oristano");
        DICT.put("Padova", "Padova");
        DICT.put("Città Metropolitana di Palermo", "Palermo");
        DICT.put("Provincia di Palermo", "Palermo");
        DICT.put("Parma", "Parma");
        DICT.put("Pavia", "Pavia");
        DICT.put("Perugia", "Perugia");
        DICT.put("Pesaro e Urbino", "Pesaro e Urbino");
        DICT.put("Pescara", "Pescara");
        DICT.put("Piacenza", "Piacenza");
        DICT.put("Pisa", "Pisa");
        DICT.put("Pistoia", "Pistoia");
        DICT.put("Pordenone", "Pordenone");
        DICT.put("Potenza", "Potenza");
        DICT.put("Prato", "Prato");
        DICT.put("Ragusa", "Ragusa");
        DICT.put("Ravenna", "Ravenna");
        DICT.put("Città Metropolitana di Reggio Calabria", "Reggio Calabria");
        DICT.put("Reggio Emilia", "Reggio Emilia");
        DICT.put("Rieti", "Rieti");
        DICT.put("Rimini", "Rimini");
        DICT.put("Città Metropolitana di Roma", "Roma");
        DICT.put("Rovigo", "Rovigo");
        DICT.put("Salerno", "Salerno");
        DICT.put("Sassari", "Sassari");
        DICT.put("Savona", "Savona");
        DICT.put("Siena", "Siena");
        DICT.put("Siracusa", "Siracusa");
        DICT.put("Sondrio", "Sondrio");
        DICT.put("Taranto", "Taranto");
        DICT.put("Teramo", "Teramo");
        DICT.put("Terni", "Terni");
        DICT.put("Città Metropolitana di Torino", "Torino");
        DICT.put("Trapani", "Trapani");
        DICT.put("Trento", "Trento");
        DICT.put("Treviso", "Treviso");
        DICT.put("Provincia di Trieste", "Trieste");
        DICT.put("Udine", "Udine");
        DICT.put("Varese", "Varese");
        DICT.put("Città Metropolitana di Venezia", "Venezia");
        DICT.put("Verbano-Cusio-Ossola", "Verbano-Cusio-Ossola");
        DICT.put("Vercelli", "Vercelli");
        DICT.put("Verona", "Verona");
        DICT.put("Vibo Valentia", "Vibo Valentia");
        DICT.put("Vicenza", "Vicenza");
        DICT.put("Viterbo", "Viterbo");
    }

    public static String getValue(String key){
        return DICT.get(key);
    }
}
