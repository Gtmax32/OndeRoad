package com.unimi.mobidev.onderoad.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Giuseppe on 22/09/2016.
 */

public class RegionProvinceDict {

    private static final TreeMap<String, List<String>> DICT;
    static{
        DICT = new TreeMap<>();
        DICT.put("Abruzzo", Arrays.asList("Chieti","L'Aquila","Pescara","Teramo"));
        DICT.put("Basilicata",Arrays.asList("Matera","Potenza"));
        DICT.put("Calabria",Arrays.asList("Catanzaro","Cosenza","Crotone","Reggio di Calabria","Vibo Valentia"));
        DICT.put("Campania",Arrays.asList("Avellino","Benevento","Caserta","Napoli","Salerno"));
        DICT.put("Emilia-Romangna",Arrays.asList("Bologna","Ferrara","Forlì-Cesena","Modena","Parma","Piacenza","Ravenna","Reggio Emilia","Rimini"));
        DICT.put("Friuli-Venezia Giulia",Arrays.asList("Gorizia","Pordenone","Trieste","Udine"));
        DICT.put("Lazio",Arrays.asList("Frosinone","Latina","Rieti","Roma","Viterbo"));
        DICT.put("Liguria",Arrays.asList("Genova","Imperia","La Spezia","Savona"));
        DICT.put("Lombardia",Arrays.asList("Bergamo","Brescia","Como","Cremona","Lecco","Lodi","Mantova","Milano","Pavia","Sondrio","Varese"));
        DICT.put("Marche",Arrays.asList("Ancona","Ascoli Piceno","Macerata","Pesaro e Urbino"));
        DICT.put("Molise",Arrays.asList("Campobasso","Isernia"));
        DICT.put("Piemonte",Arrays.asList("Alessandria","Asti","Biella","Cuneo","Novara","Torino","Verbano-Cusio-Ossola","Vercelli"));
        DICT.put("Puglia",Arrays.asList("Bari","Barletta-Andria-Trani","Brindisi","Foggia","Lecce","Taranto"));
        DICT.put("Sardegna",Arrays.asList("Cagliari","Nuoro","Oristano","Sassari"));
        DICT.put("Sicilia",Arrays.asList("Agrigento","Caltanissetta","Catania","Enna","Messina","Palermo","Ragusa","Siracusa","Trapani"));
        DICT.put("Toscana",Arrays.asList("Arezzo","Firenze","Grosseto","Livorno","Lucca","Massa-Carrara","Pisa","Pistoia","Prato","Siena"));
        DICT.put("Trentino-Alto Adige",Arrays.asList("Bolzano","Trento"));
        DICT.put("Umbria",Arrays.asList("Perugia","Terni"));
        DICT.put("Valle d'Aosta",Arrays.asList("Aosta"));
        DICT.put("Veneto",Arrays.asList("Belluno","Padova","Rovigo","Treviso","Vicenza","Venezia","Verona"));

    }

    public static ArrayList<String> getKeys(){
        ArrayList<String> keys = new ArrayList<>();

        for (String key: DICT.keySet()){
            keys.add(key);
        }

        return keys;
    }

    public static ArrayList<String> getElemFromKey(String key){
        if (DICT.containsKey(key))
            return new ArrayList<>(DICT.get(key));
        else return null;
    }

}
